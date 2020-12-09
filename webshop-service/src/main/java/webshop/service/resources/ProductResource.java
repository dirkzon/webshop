package webshop.service.resources;

import org.jvnet.hk2.annotations.Service;
import webshop.logic.interfaces.IProductService;
import webshop.service.filters.UseAuthorisationFilter;
import webshop.service.models.*;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static webshop.service.filters.Constants.USER_ID;
import static webshop.service.filters.Constants.USER_ROLE;

@Path("/products")
@Service
public class ProductResource {

    private final IProductService service;

    @Inject
    public ProductResource(IProductService service){
        this.service = service;
    }

    @Context
    ContainerRequestContext request;

    @GET
    @UseAuthorisationFilter
    @RolesAllowed({"Customer", "Retailer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{product_id}")
    public Response getProductById(@PathParam("product_id") int id){
        var product = service.getProductById(id);
        UserRole role = UserRole.valueOf(request.getProperty(USER_ROLE).toString());
        int userId = Integer.parseInt(request.getProperty(USER_ID).toString());
        if(role == UserRole.Retailer){
            if(product.getRetailer().getId() == userId) product.setCanEdit(true);
            product.setCanReview(false);
        }else{
            for(Review review : product.getReviews()){
                if(review.getCustomer().getId() == userId) product.setCanReview(false);
            }
        }
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
            int userId = Integer.parseInt(request.getProperty(USER_ID).toString());
        review.getCustomer().setId(userId);
        service.createReviewOnProductById(id,review);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
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
