package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.OpportunitiesDTO;
import dto.OpportunityDTO;
import dto.OpportunityStatussesDTO;
import errorhandling.NotFoundException;
import facades.OpportunityFacade;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

@Path("opportunity")
public class OpportunityResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    private static final OpportunityFacade FACADE = OpportunityFacade.getOpportunityFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public long allUsers() {
        return FACADE.getOpportunityCount();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{email}")
    public String getOpportunitiesByEmail(@PathParam("email") String email) throws NotFoundException {
        return GSON.toJson(FACADE.getOpportunityByEmail(email));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String getAllOpportunities() throws NotFoundException {
        OpportunityStatussesDTO opsDTO = FACADE.getAllOpportunities();
        return GSON.toJson(opsDTO);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{email}")
    @RolesAllowed("admin")
    public Response addOpportunity(String op, @PathParam("email") String email) throws Exception {
        OpportunityDTO opDTO = GSON.fromJson(op, OpportunityDTO.class);
        String response = FACADE.addOpportunity(opDTO, email);
        return Response.status(Response.Status.OK).entity(response).build();
    }

}
