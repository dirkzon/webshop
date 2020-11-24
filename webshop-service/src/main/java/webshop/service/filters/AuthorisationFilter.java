package webshop.service.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import webshop.service.models.Roles;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.List;

public class AuthorisationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext){

        final String AUTHORIZATION_PROPERTY = "Authorization";
        final String AUTHENTICATION_SCHEME = "Bearer";

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
            credentials = Jwts.parser().setSigningKey("secret").parseClaimsJws(encodedCredentials).getBody();
        } catch (Exception e) {
            Response response = Response.status(Response.Status.UNAUTHORIZED).
                    entity(e.getCause()).build();
            requestContext.abortWith(response);
            return;
        }

        final String id = credentials.getId();
        final String username = credentials.getSubject();
        final Roles role = Roles.valueOf(credentials.get("role").toString());
    }
}
