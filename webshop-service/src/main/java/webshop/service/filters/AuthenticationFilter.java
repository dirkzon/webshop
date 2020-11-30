package webshop.service.filters;

import webshop.logic.interfaces.IAccountService;
import webshop.service.models.Account;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

@UseAuthenticationFilter
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    private IAccountService accountService;

    @Override
    public void filter(ContainerRequestContext requestContext){
        final String AUTHORIZATION_PROPERTY = "Authentication";
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

        String credentials = new String(Base64.getDecoder().decode(encodedCredentials.getBytes()));

        final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        Account account = accountService.isAccountValid(username, password);
        if(account == null){
            Response response = Response.status(Response.Status.UNAUTHORIZED).
                    entity("Username and/or password is incorrect.").build();
            requestContext.abortWith(response);
            return;
        }

        requestContext.setProperty("account", account);
    }
}
