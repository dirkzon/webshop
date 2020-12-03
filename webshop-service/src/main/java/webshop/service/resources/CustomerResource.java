package webshop.service.resources;

import org.jvnet.hk2.annotations.Service;
import webshop.logic.interfaces.ICustomerService;
import webshop.service.filters.UseAuthorisationFilter;
import webshop.service.models.Customer;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
@Service
public class CustomerResource {

    private final ICustomerService service;

    @Inject
    public CustomerResource(ICustomerService service){
        this.service = service;
    }

    @GET
    @UseAuthorisationFilter
    @RolesAllowed({"Customer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{customer_id}")
    public Response getCustomerById(@PathParam("customer_id") int id){
        var customer = service.getCustomerById(id);
        if(customer != null){
            return Response.ok(customer).build();
        }else{
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("No customer could be found").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer){
        if(customer != null){
            var newCustomer = service.saveCustomer(customer);
            return Response.ok(newCustomer).build();
        }else{
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("no customer given").build();
        }
    }

    @DELETE
    @UseAuthorisationFilter
    @RolesAllowed({"Customer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{customer_id}")
    public Response removeCustomerById(@PathParam("customer_id") int id){
        if(service.removeCustomerById(id)){
            return Response.ok("Customer with id:" + id + " has been removed").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Could not remove customer").build();
    }

    @PUT
    @UseAuthorisationFilter
    @RolesAllowed({"Customer"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{customer_id}")
    public Response updateCustomerById(@PathParam("customer_id") int id, Customer customer){
        if(customer != null){
            var updatedCustomer = service.updateCustomerById(id, customer);
            return Response.ok(updatedCustomer).build();
        }else{
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("no customer given").build();
        }
    }
}
