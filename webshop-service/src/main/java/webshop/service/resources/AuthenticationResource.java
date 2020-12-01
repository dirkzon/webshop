package webshop.service.resources;

import antlr.StringUtils;
import org.jvnet.hk2.annotations.Service;
import webshop.logic.interfaces.IAccountService;
import webshop.service.filters.UseAuthenticationFilter;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import webshop.service.models.Account;

import java.util.Base64;
import java.util.StringTokenizer;

@Path("/authentication")
@Service
public class AuthenticationResource {

    @Inject
    private IAccountService service;

    @GET
    @UseAuthenticationFilter
    @Produces(MediaType.APPLICATION_JSON)
    public Response LogIn(@Context ContainerRequestContext request){
        Account account = (Account) request.getProperty("account");
        String token = service.createToken(account);
        String payload = new String(Base64.getDecoder().decode(token.split("\\.")[1].getBytes()));
        if(token != null){
            return Response.ok(payload).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
