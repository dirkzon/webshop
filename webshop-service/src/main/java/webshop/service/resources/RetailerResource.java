package webshop.service.resources;

import org.jvnet.hk2.annotations.Service;
import webshop.logic.interfaces.IRetailerService;
import webshop.service.filters.UseAuthorisationFilter;
import webshop.service.models.Product;
import webshop.service.models.Retailer;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static webshop.service.filters.Constants.USER_ID;

@Path("/retailers")
@Service
public class RetailerResource {

    private final IRetailerService service;

    @Context
    ContainerRequestContext request;

    @Inject
    public RetailerResource(IRetailerService service){
        this.service = service;
    }

    @GET
    @UseAuthorisationFilter
    @RolesAllowed({"Retailer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/me")
    public Response getMe(){
        int id = Integer.valueOf(request.getProperty(USER_ID).toString());
        Response response =  getRetailerById(id);
        return response;
    }

    @GET
    @UseAuthorisationFilter
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{retailer_id}")
    public Response getRetailerById(@PathParam("retailer_id") int id){
        var retailer = service.getRetailerById(id);
        if(retailer != null){
            return Response.ok(retailer).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Retailer was not found").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRetailer(Retailer retailer){
        if(retailer != null) {
            var newRetailer = service.saveRetailer(retailer);
            return Response.ok(newRetailer).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity("no retailer given").build();
    }

    @DELETE
    @UseAuthorisationFilter
    @RolesAllowed("Retailer")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{retailer_id}")
    public Response removeRetailerById(@PathParam("retailer_id") int id){
        if(service.removeRetailerById(id)){
            return Response.ok("Retailer with id:" + id + " has been removed").build();
        }else{
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Could not remove retailer").build();
        }
    }

    @PUT
    @UseAuthorisationFilter
    @RolesAllowed("Retailer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{retailer_id}")
    public Response updateRetailer(@PathParam("retailer_id") int id, Retailer retailer){
        if(retailer != null){
            var updatedRetailer = service.updateRetailerById(id, retailer);
            return Response.ok(updatedRetailer).build();
        }else{
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Could not remove retailer").build();
        }
    }

    @GET
    @UseAuthorisationFilter
    @RolesAllowed({"Retailer", "Customer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{retailer_id}/catalog")
    public Response getAllProductsInCatalog(@PathParam("retailer_id") int id){
        var products = service.getAllProductsInCatalog(id);
        if(!products.isEmpty()){
            return Response.ok(products).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity("no products found").build();
    }

    @POST
    @UseAuthorisationFilter
    @RolesAllowed("Retailer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{retailer_id}/catalog")
    public Response createProductInCatalog(@PathParam("retailer_id") int id , Product product){
        var newProduct = service.createNewProduct(id, product);
        return Response.ok(newProduct).build();
    }
}
