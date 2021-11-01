package nl.thom.marktplaats.domain;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Email;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "Gebruiker.findAll",
        query = "SELECT p FROM Gebruiker p"
)
@NamedQuery(name = "Gebruiker.findByUsernameAndPassword",
        query = "SELECT p FROM Gebruiker p WHERE p.username = :username AND p.password = :password"
)

public class Gebruiker extends AbstractEntity implements Identifiable<Integer> {
    public String username;

//    @Email
//    @Column(unique = true)
    String email;

    String password;
    boolean obeyRules;
    byte bezorgwijzen;

    //public List<Integer> advertenties;
    //public List<Advertentie> advertenties = new ArrayList(); // Toekomstmuziek


    public Integer getId() {
        return id;
    }

    public void setBezorgwijzen(byte b) {
        this.bezorgwijzen = b;
    }

    @Override
    public String toString() {
        return "User: " + username + ", " + email + ", " + password;
    }
}
