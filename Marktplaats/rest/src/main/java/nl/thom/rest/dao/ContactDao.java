package nl.thom.rest.dao;

import nl.thom.rest.domain.Contact;

import static nl.thom.rest.util.Responses.throwBadRequest;


import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
public class ContactDao {

    @PersistenceContext // Container managed EntityManager, not via @Inject
    private EntityManager em; // container, geef mij een EntityManager

    private final Contact.ContactBuilder cb = Contact.builder();

    private final List<Contact> contacts = new ArrayList<>(List.of(
            cb.firstName("Thom").surname("Jansz").email("thom@hoi.nl").id(1L).build(),
            cb.firstName("Ollie").surname("Kat").email("ollie@mau.nl").id(2L).build(),
            cb.firstName("Lotte").surname("Ruitje").email("lotje@hoi.nl").id(3L).build(),
            cb.firstName("Bella").surname("Kat").email("missbella@mau.nl").id(4L).build()
    ));

    public List<Contact> getContacts(String q) {
        return q == null ?
                em.createNamedQuery("Contact.findAll", Contact.class)
                        .getResultList() :
                em.createNamedQuery("Contact.findByQ", Contact.class)
                        .setParameter("q", "%" + q + "%")
                        .getResultList();
    }

    public Optional<Contact> getContact(Long id) {
        return Optional.ofNullable(em.find(Contact.class, id));
    }

    @TransactionAttribute(REQUIRED)  // = default; whole annotation can be omitted when choosing REQUIRED.
    //                                  Deze methode wordt in een databasetransactie op de server uitgevoerd.
    //                                  Als er al een transactie loopt, gebruikt de server die, anders maakt hij een nieuwe transactie aan.
    public Contact add(Contact c) {
        // no em.gettransation.begin/commit/rollback: is managed by (EJB) container at the server
        em.persist(c);
        return c;
    }

    public void remove(Long id) {
        getContact(id).ifPresentOrElse(em::remove, throwBadRequest(id));
    }

    public Contact update(Long id, Contact updatedContact) {
        updatedContact.setId(id);
        return em.merge(updatedContact);
    }
    // public void remove(Long id) {
    //     getContact(id).ifPresentOrElse(em::remove, throwBadRequest(id));
    // }
    //
    // public Contact update(Long id, Contact updatedContact) {
    //     updatedContact.setId(id);
    //     return em.merge(updatedContact);
    // }
    //
    //
    // public Contact add(Contact input) {
    //     // TODO: getMaxId in aparte methode
    //     Long max = this.contacts.stream()
    //             .mapToLong(Contact::getId)
    //             .max().orElse(-1L);
    //     input.setId(max);
    //     contacts.add(input);
    //
    //     return input;
    // }
    //
    // public Optional<Contact> getContact(long id) {
    //     return contacts.stream()
    //             .filter(c -> c.getId() == id)
    //             .findAny();
    // }
    //
    // public void remove(long id) {
    //     Contact contact = getContact(id).get();
    //     this.contacts.remove(contact);
    // }
    //
    // public Contact update(long id, Contact updatedContact) {
    //     Contact contact = getContact(id).get();
    //     int i = this.contacts.indexOf(contact);
    //     this.contacts.set(i, updatedContact);
    //     return updatedContact; //TODO: haal weer opnieuw op uit db
    //
    // }
}
