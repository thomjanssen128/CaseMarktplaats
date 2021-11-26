package nl.thom.rest.resources;

import nl.thom.rest.dao.ContactDao;
import nl.thom.rest.domain.Contact;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("contacts")
public class ContactsResource {

    @Inject
    private ContactDao contactDao;

    @Inject
    private ContactResource contactResource;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Contact> getAll(@QueryParam("q") String q) {
        return contactDao.getContacts(q);
    }

    @Path("{id}")
    public ContactResource get(@PathParam("id") long id) {
        this.contactResource.setId(id);
        return  this.contactResource;
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Contact add(Contact input) {
        return contactDao.add(input);
    }

    // PUT

    // DEL
}
