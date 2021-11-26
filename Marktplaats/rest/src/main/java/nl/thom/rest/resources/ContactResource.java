package nl.thom.rest.resources;

import nl.thom.rest.dao.ContactDao;
import nl.thom.rest.domain.Contact;

import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static nl.thom.rest.util.Responses.badRequest;

public class ContactResource {

    private long id;

    @Inject
    private ContactDao contactDao;

    @GET
    @Produces(APPLICATION_JSON)
    public Contact get() {
        return contactDao.getContact(this.id).orElseThrow(() -> badRequest(this.id));
    }

    @DELETE
    public void delete() {
        contactDao.remove(this.id);
    }

    @PUT
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Contact put(Contact updatedContact) {
        return contactDao.update(this.id, updatedContact);
    }

    public void setId(long id) {
        this.id = id;
    }
}
