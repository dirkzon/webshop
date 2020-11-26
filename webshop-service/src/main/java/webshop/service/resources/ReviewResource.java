package webshop.service.resources;

import org.jvnet.hk2.annotations.Service;
import webshop.logic.interfaces.IReviewService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reviews")
@Service
public class ReviewResource {

    @Inject
    private IReviewService service;

    @DELETE
    @RolesAllowed("Customer")
    @Path("/{review_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteReviewById(@PathParam("review_id") String id){
        if(id != null){
            service.DeleteReviewById(id);
            return Response.ok().build();
        }else{
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("No id given").build();
        }
    }
}
