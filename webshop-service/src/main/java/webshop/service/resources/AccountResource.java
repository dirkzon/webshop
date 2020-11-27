package webshop.service.resources;

import net.bytebuddy.dynamic.loading.ClassInjector;
import org.jvnet.hk2.annotations.Service;
import webshop.logic.interfaces.IUserService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/me")
@Service
public class AccountResource {



    @GET
    @RolesAllowed({"Retailer", "Customer"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetUserAccount(){
        return null;
    }
}
