package webshop.service.resources;

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
        if(token != null){
            return Response.ok(token).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
