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
import webshop.service.models.AuthenticationData;

import java.time.LocalDateTime;

@Path("/authentication")
@Service
public class AuthenticationResource {

    private final IAccountService service;

    @Context
    ContainerRequestContext request;

    @Inject
    public AuthenticationResource(IAccountService service){
        this.service = service;
    }

    @GET
    @UseAuthenticationFilter
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(){
        try{
            Account account = (Account) request.getProperty("account");
            String token = service.createToken(account);
            AuthenticationData data = new AuthenticationData(token, account.getRole(), LocalDateTime.now());
            return Response.ok(data).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
