package webshop.service.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jvnet.hk2.annotations.Service;
import webshop.persistence.HibernateProxyTypeAdapter;
import webshop.service.models.Customer;
import webshop.persistence.interfaces.ICustomerRepository;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
@Service
public class CustomerResource {

    @Inject
    private ICustomerRepository repository;

    private GsonBuilder gsonBuilder;

    public CustomerResource(){
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
    }

    @GET
    @RolesAllowed({"Customer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{customer_id}")
    public Response GetCustomerById(@PathParam("customer_id") String id){
        Gson gson = gsonBuilder.create();
        var customer = repository.GetUserById(id);
        if(customer != null){
            return Response.ok(gson.toJson(customer)).build();
        }else{
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("id was not valid").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response CreateCustomer(Customer customer){
        Gson gson = gsonBuilder.create();
        if(customer != null){
            var newCustomer = repository.CreateUser(customer);
            return Response.ok(gson.toJson(newCustomer)).build();
        }else{
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("no customer given").build();
        }
    }

    @DELETE
    @RolesAllowed({"Customer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{customer_id}")
    public Response RemoveCustomerById(@PathParam("customer_id") String id){
        repository.RemoveUserById(id);
        return Response.ok().build();
    }

    @PUT
    @RolesAllowed({"Customer"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{customer_id}")
    public Response UpdateCustomerById(@PathParam("customer_id") String id, Customer customer){
        Gson gson = gsonBuilder.create();
        if(customer != null){
            var updatedCustomer = repository.UpdateUserById(id, customer);
            return Response.ok(gson.toJson(updatedCustomer)).build();
        }else{
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("no customer given").build();
        }
    }
}
