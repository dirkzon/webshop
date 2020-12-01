package webshop.service.resources;

import org.jvnet.hk2.annotations.Service;
import webshop.logic.interfaces.IProductService;
import webshop.service.filters.UseAuthorisationFilter;
import webshop.service.models.BrowseVars;
import webshop.service.models.Product;
import webshop.service.models.Review;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
@Service
public class ProductResource {

    private final IProductService service;

    @Inject
    public ProductResource(IProductService service){
        this.service = service;
    }

    @GET
    @UseAuthorisationFilter
    @RolesAllowed({"Customer", "Retailer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{product_id}")
    public Response getProductById(@PathParam("product_id") int id){
        var product = service.getProductById(id);
        if(product != null) {
            return Response.ok(product).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity("no product found").build();
    }

    @DELETE
    @UseAuthorisationFilter
    @RolesAllowed("Retailer")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{product_id}")
    public Response removeProductById(@PathParam("product_id") int id){
        service.removeProductById(id);
        return Response.ok().build();
    }

    @PUT
    @UseAuthorisationFilter
    @RolesAllowed("Retailer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{product_id}")
    public Response updateProductById(@PathParam("product_id") int id, Product product){
        var updatedProduct = service.updateProductById(id, product);
        if(updatedProduct != null){
            return Response.ok(updatedProduct).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Product was not found").build();
    }

    @POST
    @UseAuthorisationFilter
    @RolesAllowed("Customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{product_id}/reviews")
    public Response createReviewOnProductById(@PathParam("product_id") int id, Review review){
        service.createReviewOnProductById(id,review);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @UseAuthorisationFilter
    @RolesAllowed({"Customer", "Retailer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/browse")
    public Response browseProducts(BrowseVars variables){
        List<Product> products = service.browseProducts(variables);
        if(!products.isEmpty()){
            return Response.ok(products).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity("No products were found").build();
    }
}
