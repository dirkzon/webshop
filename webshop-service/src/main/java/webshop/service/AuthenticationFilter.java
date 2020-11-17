package webshop.service;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        final String AUTHORIZATION_PROPERTY = "Authorization";
        final String AUTHENTICATION_SCHEME = "Bearer";

        final MultivaluedMap<String, String> headers = requestContext.getHeaders();

        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);



        //if (authorization == null || authorization.isEmpty()) {
        //    Response response = Response.status(Response.Status.UNAUTHORIZED).
        //            entity("Missing username and/or password.").build();
        //    requestContext.abortWith(response);
        //    return;
        //}

        final String encodedCredentials = authorization.get(0).replaceAll(AUTHENTICATION_SCHEME + " ", "");

        String credentials = new String(Base64.getDecoder().decode(encodedCredentials.getBytes()));

        final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        //if (!isValidUser(username, password)) {
        //    Response response = Response.status(Response.Status.UNAUTHORIZED).
        //            entity("Invalid username and/or password.").build();
        //    requestContext.abortWith(response);
        //    return;
        //}
    }
}
