package nl.thom.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@XmlRootElement
@Entity
@NamedQueries({
        @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
        @NamedQuery(name = "Contact.findByQ", query = "SELECT c FROM Contact c WHERE c.surname LIKE :q OR c.firstName like :q"),
        @NamedQuery(name = "Contact.find", query = "SELECT c FROM Contact c WHERE c.id=:id")
})
public class Contact {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String surname;
    private String email;
}
