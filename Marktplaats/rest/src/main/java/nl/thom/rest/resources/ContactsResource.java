package nl.thom.rest.resources;

import nl.thom.rest.dao.ContactDao;
import nl.thom.rest.domain.Contact;

import javax.persistence.Column;
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static nl.thom.rest.util.Responses.badRequest;

@Path("contacts")
public class ContactsResource {

    private final ContactDao contactDao = new ContactDao();

    @GET
    @Produces(APPLICATION_JSON)
    public List<Contact> getContacts() {
        return contactDao.getContacts();
    }

    @GET
    @Path("{id}")
    @Produces(APPLICATION_JSON)
    public Contact get(@PathParam("id") long id) {
        return contactDao.get(id).orElseThrow(() -> badRequest(id));
    }

    // public Contact getQ(@PathParam("q" String query) {
    //
    // })

    // @POST
    // @Produces(APPLICATION_JSON)
    // @Consumes(APPLICATION_JSON)
    // public Contact postContact(Contact contact) {
    //     contactDao.add(contact);
    // }
}
