package nl.thom.marktplaats.domain;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

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
@NamedQuery(name = "Gebruiker.findByUsername",
        query = "SELECT p FROM Gebruiker p WHERE p.username = :username"
)
@NamedQuery(name = "Gebruiker.findByEmail",
        query = "SELECT p FROM Gebruiker p WHERE p.email = :email"
)

public class Gebruiker extends AbstractEntity implements Identifiable<Integer> {
    @Column(unique = true)
    public String username;
    //@Email
    @Column(unique = true)
    String email;
    String password;
    boolean obeyRules;
    int bezorgwijzen;

    @Embedded
    Adres adres;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User: " + username + ", " + email + ", " + password;
    }
}
