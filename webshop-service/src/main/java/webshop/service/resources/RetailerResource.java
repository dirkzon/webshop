package webshop.service.resources;

import org.jvnet.hk2.annotations.Service;
import webshop.logic.interfaces.IRetailerService;
import webshop.service.filters.UseAuthorisationFilter;
import webshop.service.models.Product;
import webshop.service.models.Retailer;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/retailers")
@Service
public class RetailerResource {

    @Inject
    private IRetailerService service;

    @GET
    @UseAuthorisationFilter
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{retailer_id}")
    public Response GetRetailerById(@PathParam("retailer_id") int id){
        var retailer = service.getRetailerById(id);
        if(retailer != null){
            return Response.ok(retailer).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Retailer was not found").build();
    }

    @POST
    @UseAuthorisationFilter
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response CreateRetailer(Retailer retailer){
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
    public Response RemoveRetailerById(@PathParam("retailer_id") int id){
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
    public Response UpdateRetailer(@PathParam("retailer_id") int id, Retailer retailer){
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
    public Response GetAllProductsInCatalog(@PathParam("retailer_id") int id){
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
    public Response CreateProductInCatalog(@PathParam("retailer_id") int id , Product product){
        var newProduct = service.createNewProduct(id, product);
        return Response.ok(newProduct).build();
    }
}
