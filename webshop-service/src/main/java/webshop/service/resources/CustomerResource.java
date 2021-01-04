package webshop.service.resources;

import org.jvnet.hk2.annotations.Service;
import webshop.logic.interfaces.ICustomerService;
import webshop.service.AllowedRoles;
import webshop.service.filters.UseAuthorisationFilter;
import webshop.service.models.Customer;
import webshop.service.models.UserRole;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static webshop.service.filters.Constants.USER_ID;

@Path("/customers")
@Service
public class CustomerResource {

    private final ICustomerService service;

    @Context
    ContainerRequestContext request;

    @Inject
    public CustomerResource(ICustomerService service){
        this.service = service;
    }

    @GET
    @UseAuthorisationFilter
    @AllowedRoles({UserRole.CUSTOMER})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/me")
    public Response getMe(){
        try{
            int id = Integer.parseInt(request.getProperty(USER_ID).toString());
            Customer customer =  service.getCustomerById(id);
            return Response.ok(customer).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @UseAuthorisationFilter
    @AllowedRoles({UserRole.CUSTOMER})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{customer_id}")
    public Response getCustomerById(@PathParam("customer_id") int id){
        try{
            Customer customer = service.getCustomerById(id);
            return Response.ok(customer).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer){
        try{
            Customer newCustomer = service.saveCustomer(customer);
            return Response.ok(newCustomer).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @UseAuthorisationFilter
    @AllowedRoles({UserRole.CUSTOMER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeCustomerById() {
        try {
            int id = Integer.parseInt(request.getProperty(USER_ID).toString());
            service.removeCustomerById(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @UseAuthorisationFilter
    @AllowedRoles({UserRole.CUSTOMER})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomerById(Customer customer){
        try{
            int id = Integer.parseInt(request.getProperty(USER_ID).toString());
            var updatedCustomer = service.updateCustomerById(id, customer);
            return Response.ok(updatedCustomer).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
