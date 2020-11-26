package webshop.service.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jvnet.hk2.annotations.Service;
import webshop.logic.services.ProductLogic;
import webshop.persistence.HibernateProxyTypeAdapter;
import webshop.service.gsonExclusionStrategies.IgnoreAddress;
import webshop.service.models.Product;
import webshop.service.models.ProductReview;
import webshop.persistence.interfaces.IProductRepository;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
@Service
public class ProductResource {

    @Inject
    private IProductRepository repository;

    private ProductLogic logic= new ProductLogic();

    private GsonBuilder gsonBuilder;

    public ProductResource(){
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
    }

    @GET
    @RolesAllowed({"Customer", "Retailer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{product_id}")
    public Response GetProductById(@PathParam("product_id") String id){
        Gson gson = gsonBuilder.create();
        String test = "";
        var product = repository.GetProductById(id);
        test = gson.toJson(product);
        if(product != null) {
            return Response.ok(test).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity("no product found").build();
    }

    @DELETE
    @RolesAllowed("Retailer")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{product_id}")
    public Response DeleteProductById(@PathParam("product_id") String id){
        repository.DeleteProductById(id);
        return Response.ok().build();
    }

    @PUT
    @RolesAllowed("Retailer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{product_id}")
    public Response UpdateProductById(@PathParam("product_id") String id, Product product){
        Gson gson = gsonBuilder.create();
        var updatedProduct = repository.UpdateProductById(id, product);
        if(updatedProduct == null){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Product was not found").build();
        }
        return Response.ok(gson.toJson(updatedProduct)).build();
    }

    @POST
    @RolesAllowed("Customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{product_id}/reviews")
    public Response CreateReviewOnProductById(@PathParam("product_id") String id, ProductReview review){
        logic.CreateReview(id,review);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @RolesAllowed({"Customer", "Retailer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{product_id}/reviews")
    public Response GetAllReviewsOnProductById(@PathParam("product_id") String id){
        Gson gson = gsonBuilder.setExclusionStrategies(new IgnoreAddress()).create();;
        var reviews = repository.GetAllReviewsOnProductById(id);
        return Response.ok(gson.toJson(reviews)).build();
    }

    @GET
    @RolesAllowed({"Customer", "Retailer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/browse")
    public Response BrowseProducts(@QueryParam("min_price") int minPrice,
                                  @QueryParam("max_price") int maxPrice,
                                  @QueryParam("query") String query,
                                  @QueryParam("category") String category,
                                  @QueryParam("target_rating") int targetRating){
        Gson gson = gsonBuilder.create();
        var products= repository.BrowseProducts(minPrice, maxPrice, query, category, targetRating);
        if(products.size() == 0){
            return Response.status(Response.Status.NO_CONTENT).entity("No products where found").build();
        }
        var json = gson.toJson(products);
        return Response.ok(json).build();
    }

    @GET
    @RolesAllowed({"Customer", "Retailer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categories")
    public Response GetAllCategories(@QueryParam("limit") int limit, @QueryParam("offset") int offset){
        Gson gson = gsonBuilder.create();
        var categories = repository.GetAllCategories();
        return Response.ok(gson.toJson(categories)).build();
    }

    @GET
    @RolesAllowed({"Customer", "Retailer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categories/{category_id}")
    public Response GetCategoryById(@PathParam("category_id") String id){
        Gson gson = gsonBuilder.create();
        var category = repository.GetCategoryById(id);
        return Response.ok(gson.toJson(category)).build();
    }
}
