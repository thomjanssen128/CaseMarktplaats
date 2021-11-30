package nl.thom.rest.resources;

import nl.thom.rest.dao.ContactDao;
import nl.thom.rest.domain.Contact;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

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
    @Operation(description = "Gets all contacts or filtered by q.")
    public List<Contact> getAll(@Parameter(description = "Search on firstname and/or surname")
                                @QueryParam("q") String q) {
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
