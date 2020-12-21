package webshop.service.resources;

import org.jvnet.hk2.annotations.Service;
import webshop.logic.interfaces.IProductService;
import webshop.service.filters.UseAuthorisationFilter;
import webshop.service.models.*;

import javax.annotation.security.PermitAll;
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
        try{
            Product product = service.getProductById(id);
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
            return Response.ok(product).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @UseAuthorisationFilter
    @RolesAllowed("Retailer")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{product_id}")
    public Response removeProductById(@PathParam("product_id") int id){
        try{
            service.removeProductById(id);
            return Response.ok().build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @UseAuthorisationFilter
    @RolesAllowed("Retailer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{product_id}")
    public Response updateProductById(@PathParam("product_id") int id, Product product){
        try{
            int userId = Integer.parseInt(request.getProperty(USER_ID).toString());
            if(product.getRetailer().getId() == userId) {
                var updatedProduct = service.updateProductById(id, product);
                return Response.ok(updatedProduct).build();
            }
            return Response.status(Response.Status.FORBIDDEN).entity("Cannot update others products").build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @UseAuthorisationFilter
    @RolesAllowed("Customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{product_id}/reviews")
    public Response createReviewOnProductById(@PathParam("product_id") int id, Review review){
        try{
            int userId = Integer.parseInt(request.getProperty(USER_ID).toString());
            review.getCustomer().setId(userId);
            service.createReviewOnProductById(id,review);
            return Response.status(Response.Status.CREATED).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/browse")
    public Response browseProducts(BrowseVars variables){
        try{
            List<Product> products = service.browseProducts(variables);
            return Response.ok(products).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
