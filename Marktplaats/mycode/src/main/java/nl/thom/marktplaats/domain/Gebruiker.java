package nl.thom.marktplaats.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "Gebruiker.findAll", query = "SELECT g FROM Gebruiker g")
@NamedQuery(name = "Gebruiker.findByUsernameAndPassword", query = "SELECT g FROM Gebruiker g WHERE g.username = :username AND g.password = :password")
@NamedQuery(name = "Gebruiker.findByUsername", query = "SELECT g FROM Gebruiker g WHERE g.username = :username")
@NamedQuery(name = "Gebruiker.findByEmail", query = "SELECT g FROM Gebruiker g WHERE g.email = :email")
@NamedQuery(name = "Gebruiker.findAllMyAds", query = "SELECT a FROM Advertentie a WHERE a.owner.id = :id")

public class Gebruiker extends AbstractEntity implements Identifiable<Integer> {
    @Column(unique = true)
    private String username;

    @Email
    @Column(unique = true)
    private String email;

    private String password;
    private boolean obeyRules;
    private int bezorgwijzen;

    @Embedded
    private Adres adres;

    @Override
    public String toString() {
        return "User: " + username + ", " + email + ", " + password;
    }
}
