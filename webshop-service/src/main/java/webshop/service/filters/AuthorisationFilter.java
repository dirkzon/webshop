package webshop.service.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import webshop.service.models.UserRole;

import javax.crypto.SecretKey;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static webshop.logic.services.KeyService.GetKeyFromStore;
import static webshop.service.filters.Constants.*;

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
            SecretKey secretKey = GetKeyFromStore("jwt", "Webshop-service\\Keystore.jks");
            credentials = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(encodedCredentials)
                    .getBody();
        } catch (Exception e) {
            Response response = Response.status(Response.Status.UNAUTHORIZED).
                    entity(e.getCause()).build();
            requestContext.abortWith(response);
            return;
        }

        final UserRole role = UserRole.valueOf(credentials.get(USER_ROLE).toString());

        if(!isUserAllowed(role)){
            Response response = Response.status(Response.Status.FORBIDDEN).
                    entity("You do not have the right role(s).").build();
            requestContext.abortWith(response);
        }

        final String userId = credentials.getId();

        requestContext.setProperty(USER_ROLE, role);
        requestContext.setProperty(USER_ID, userId);
    }

    private Boolean isUserAllowed(UserRole role){
        if(!resourceInfo.getResourceMethod().isAnnotationPresent(AllowedRoles.class)) return true;
        UserRole[] allowedRoles = resourceInfo.getResourceMethod().getAnnotation(AllowedRoles.class).value();
        return Arrays.asList(allowedRoles).contains(role);
    }
}
