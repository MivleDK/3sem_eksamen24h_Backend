package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ContactDTO;
import dto.ContactsDTO;
import errorhandling.NotFoundException;
import facades.ContactFacade;
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
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

@Path("contacts")
public class ContactResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    private static final ContactFacade FACADE = ContactFacade.getContactFacade(EMF);
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
    public String allContactsCount() {
        return GSON.toJson(FACADE.getContactCount());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String getAllContacts() throws NotFoundException{
        ContactsDTO cDTO = FACADE.getAllContacts();
        return GSON.toJson(cDTO);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{email}")
    public String getContactByEmail(@PathParam("email") String email) throws NotFoundException{
        return GSON.toJson(FACADE.getContact(email));
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addContact(String contact) throws Exception {
        ContactDTO c = GSON.fromJson(contact, ContactDTO.class);
        ContactDTO newContact = FACADE.addContact(c);
        return GSON.toJson(newContact);
    }
    
}
