package nl.thom.rest.dao;

import nl.thom.rest.domain.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactDao {

    private final Contact.ContactBuilder cb = Contact.builder();

    private final List<Contact> contacts = new ArrayList<>(List.of(
            cb.firstName("Thom").surname("Jansz").email("thom@hoi.nl").id(1L).build(),
            cb.firstName("Ollie").surname("Kat").email("ollie@mau.nl").id(2L).build(),
            cb.firstName("Lotte").surname("Ruitje").email("lotje@hoi.nl").id(3L).build()
    ));

    public List<Contact> getContacts() {
        return contacts;
    }

    public Optional<Contact> get(long id) {
        return contacts.stream()
                .filter(c -> c.getId() == id)
                .findAny();
    }

    public void add(Contact input) {
        Long max = this.contacts.stream()
                .mapToLong(Contact::getId)
                .max().orElse(-1L);
        input.setId(max);
        contacts.add(input);
    }
}
