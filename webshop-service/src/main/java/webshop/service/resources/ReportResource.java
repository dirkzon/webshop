package webshop.service.resources;

import org.jvnet.hk2.annotations.Service;
import webshop.logic.interfaces.IReportService;
import webshop.service.AllowedRoles;
import webshop.service.filters.UseAuthorisationFilter;
import webshop.service.models.Report;
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

@Path("/reports")
@Service
public class ReportResource {

    private final IReportService service;

    @Context
    ContainerRequestContext request;

    @Inject
    public ReportResource(IReportService service){
        this.service = service;
    }

    @GET
    @UseAuthorisationFilter
    @AllowedRoles({UserRole.RETAILER})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{report_id}")
    public Response getReportById(@PathParam("report_id") int id){
        try{
            Report report = service.getReportById(id);
            return Response.ok(report).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @UseAuthorisationFilter
    @AllowedRoles({UserRole.RETAILER})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllReportsForRetailer(){
        try{
            int id = Integer.parseInt(request.getProperty(USER_ID).toString());
            List<Report> reports = service.getAllReportsForRetailer(id);
            return Response.ok(reports).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @UseAuthorisationFilter
    @AllowedRoles({UserRole.CUSTOMER})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportReview(Report report){
        try{
            Report newReport = service.reportReview(report);
            return Response.ok(newReport).build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @UseAuthorisationFilter
    @AllowedRoles({UserRole.CUSTOMER})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{report_id}")
    public Response removeReview(@PathParam("report_id") int id){
        try{
            service.removeReport(id);
            return Response.noContent().build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @UseAuthorisationFilter
    @AllowedRoles({UserRole.RETAILER})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{report_id}")
    public Response dismissReport(@PathParam("report_id") int id){
        try{
            service.dismissReport(id);
            return Response.noContent().build();
        }catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
