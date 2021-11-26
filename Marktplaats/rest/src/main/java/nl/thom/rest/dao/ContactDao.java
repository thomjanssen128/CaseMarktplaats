package nl.thom.rest.dao;

import nl.thom.rest.domain.Contact;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class ContactDao {

    private final Contact.ContactBuilder cb = Contact.builder();

    private final List<Contact> contacts = new ArrayList<>(List.of(
            cb.firstName("Thom").surname("Jansz").email("thom@hoi.nl").id(1L).build(),
            cb.firstName("Ollie").surname("Kat").email("ollie@mau.nl").id(2L).build(),
            cb.firstName("Lotte").surname("Ruitje").email("lotje@hoi.nl").id(3L).build(),
            cb.firstName("Bella").surname("Kat").email("missbella@mau.nl").id(4L).build()
    ));

    public List<Contact> getContacts(String q) {
        return contacts;
    }

    public Optional<Contact> get(long id) {
        return contacts.stream()
                .filter(c -> c.getId() == id)
                .findAny();
    }

    public Contact add(Contact input) {
        // TODO: getMaxId in aparte methode
        Long max = this.contacts.stream()
                .mapToLong(Contact::getId)
                .max().orElse(-1L);
        input.setId(max);
        contacts.add(input);

        return input;
    }

    public Optional<Contact> getContact(long id) {
        return contacts.stream()
                .filter(c -> c.getId() == id)
                .findAny();
    }

    public void remove(long id) {
        Contact contact = getContact(id).get();
        this.contacts.remove(contact);
    }

    public Contact update(long id, Contact updatedContact) {
        Contact contact = getContact(id).get();
        int i = this.contacts.indexOf(contact);
        this.contacts.set(i, updatedContact);
        return updatedContact; //TODO: haal weer opnieuw op uit db

    }
}
