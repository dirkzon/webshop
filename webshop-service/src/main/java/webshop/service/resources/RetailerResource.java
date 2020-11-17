package webshop.service.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jvnet.hk2.annotations.Service;
import webshop.persistence.HibernateProxyTypeAdapter;
import webshop.service.models.Product;
import webshop.service.models.Retailer;
import webshop.persistence.interfaces.IRetailerRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/retailers")
@Service
public class RetailerResource {

    @Inject
    private IRetailerRepository repository;

    private GsonBuilder gsonBuilder;

    public RetailerResource(){
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{retailer_id}")
    public Response GetRetailerById(@PathParam("retailer_id") String id){
        Gson gson = gsonBuilder.create();
        var retailer = repository.GetUserById(id);
        if(retailer != null){
            return Response.ok(gson.toJson(retailer)).build();
        }else{
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("id was not valid").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response CreateRetailer(Retailer retailer){
        Gson gson = gsonBuilder.create();
        var newRetailer = repository.CreateUser(retailer);
        return Response.ok(gson.toJson(newRetailer)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{retailer_id}")
    public Response RemoveRetailerById(@PathParam("retailer_id") String id){
        if(id != null){
            repository.RemoveUserById(id);
            return Response.ok().build();
        }else{
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("No id given").build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{retailer_id}")
    public Response UpdateRetailer(@PathParam("retailer_id") String id, Retailer retailer){
        Gson gson = gsonBuilder.create();
        if(id != null){
            var updatedRetailer = repository.UpdateUserById(id, retailer);
            return Response.ok(gson.toJson(updatedRetailer)).build();
        }else{
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("No id given").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{retailer_id}/catalog")
    public Response GetAllProductsInCatalog(@PathParam("retailer_id") String id){
        Gson gson = gsonBuilder.create();
        var products = repository.GetAllProductsInCatalog(id);
        if(products.size() == 0){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("no products found").build();
        }
        return Response.ok(gson.toJson(products)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{retailer_id}/catalog")
    public Response CreateProductInCatalog(@PathParam("retailer_id") String id ,Product product){
        Gson gson = gsonBuilder.create();
        var newProduct = repository.CreateNewProductInCatalog(id, product);
        return Response.ok(gson.toJson(newProduct)).build();
    }
}
