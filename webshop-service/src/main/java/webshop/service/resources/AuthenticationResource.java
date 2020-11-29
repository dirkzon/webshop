package webshop.service.resources;

import org.jvnet.hk2.annotations.Service;
import webshop.service.filters.UseAuthenticationFilter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
@Service
public class AuthenticationResource {

    @GET
    @UseAuthenticationFilter
    @Produces(MediaType.APPLICATION_JSON)
    public Response LogIn(){
        String token = "fdsa";
        if(token != null){
            return Response.ok(token).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
