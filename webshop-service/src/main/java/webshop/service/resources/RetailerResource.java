package webshop.service.resources;

import org.jvnet.hk2.annotations.Service;
import webshop.logic.interfaces.IRetailerService;
import webshop.service.AllowedRoles;
import webshop.service.filters.UseAuthorisationFilter;
import webshop.service.models.Product;
import webshop.service.models.Retailer;
import webshop.service.models.UserRole;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

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
    @AllowedRoles({UserRole.CUSTOMER})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/me")
    public Response getMe(){
        try{
            int id = Integer.parseInt(request.getProperty(USER_ID).toString());
            Retailer retailer =  service.getRetailerById(id);
            return Response.ok(retailer).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @UseAuthorisationFilter
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{retailer_id}")
    public Response getRetailerById(@PathParam("retailer_id") int id){
        try{
            Retailer retailer = service.getRetailerById(id);
            return Response.ok(retailer).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRetailer(Retailer retailer){
        try{
            Retailer newRetailer = service.saveRetailer(retailer);
            return Response.ok(newRetailer).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @UseAuthorisationFilter
    @AllowedRoles({UserRole.RETAILER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeRetailerById(){
        try {
            int id = Integer.parseInt(request.getProperty(USER_ID).toString());
            service.removeRetailerById(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @UseAuthorisationFilter
    @AllowedRoles({UserRole.RETAILER})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRetailer(Retailer retailer){
        try{
            int id = Integer.parseInt(request.getProperty(USER_ID).toString());
            Retailer updatedCustomer = service.updateRetailerById(id, retailer);
            return Response.ok(updatedCustomer).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @UseAuthorisationFilter
    @AllowedRoles({UserRole.CUSTOMER, UserRole.RETAILER})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{retailer_id}/catalog")
    public Response getAllProductsInCatalog(@PathParam("retailer_id") int id){
        try{
            List<Product> products = service.getAllProductsInCatalog(id);
            return Response.ok(products).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @UseAuthorisationFilter
    @AllowedRoles({UserRole.RETAILER})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/catalog")
    public Response createProductInCatalog(Product product){
        try{
            int id = Integer.parseInt(request.getProperty(USER_ID).toString());
            Product newProduct = service.createNewProduct(id, product);
            return Response.ok(newProduct).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
