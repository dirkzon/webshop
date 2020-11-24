package webshop.service.resources;

import org.jvnet.hk2.annotations.Service;
import webshop.logic.interfaces.IAuthenticationService;
import webshop.service.filters.UseAuthenticationFilter;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/authentication")
@Service
public class AuthenticationResource {

    @Inject
    private IAuthenticationService service;

    @GET
    @UseAuthenticationFilter
    @Produces(MediaType.APPLICATION_JSON)
    public Response LogIn(@Context HttpHeaders httpHeaders){
        String test = httpHeaders.getHeaderString("Authentication");
        String token = service.ValidateUser(test);
        if(token != null){
            return Response.ok(token).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
