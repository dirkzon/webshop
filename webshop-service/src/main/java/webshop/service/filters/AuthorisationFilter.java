package webshop.service.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static webshop.service.filters.Constants.AUTHENTICATION_SCHEME;

@UseAuthorisationFilter
public class AuthorisationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext){

        final String AUTHORIZATION_PROPERTY = "Authorization";

        final MultivaluedMap<String, String> headers = requestContext.getHeaders();

        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

        if (authorization == null || authorization.isEmpty()) {
            Response response = Response.status(Response.Status.UNAUTHORIZED).
                    entity("Missing username and/or password.").build();
            requestContext.abortWith(response);
            return;
        }

        final String encodedCredentials = authorization.get(0).replace(AUTHENTICATION_SCHEME + " ", "");

        Claims credentials;

        try {
            credentials = Jwts.parser().setSigningKey("c2VjcmV0").parseClaimsJws(encodedCredentials).getBody();
        } catch (Exception e) {
            Response response = Response.status(Response.Status.UNAUTHORIZED).
                    entity(e.getCause()).build();
            requestContext.abortWith(response);
            return;
        }

        final String role = credentials.get("role").toString();

        if(!isUserAllowed(role)){
            Response response = Response.status(Response.Status.FORBIDDEN).
                    entity("You do not have the right role(s).").build();
            requestContext.abortWith(response);
        }
    }

    private Boolean isUserAllowed(String role){
        if(!resourceInfo.getResourceMethod().isAnnotationPresent(RolesAllowed.class)) return true;
        String[] allowedRoles = resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class).value();
        return Arrays.asList(allowedRoles).contains(role);
    }
}
