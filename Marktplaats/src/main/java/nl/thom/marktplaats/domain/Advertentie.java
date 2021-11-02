package nl.thom.marktplaats.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

@NamedQuery(name = "Advertentie.findAll",
        query = "SELECT p FROM Advertentie p"
)
@NamedQuery(name = "Advertentie.getAdsFromUser",
        query = "SELECT p FROM Advertentie p WHERE p.ownerId = :id"
)

public class Advertentie extends AbstractEntity implements Identifiable<Integer> {
    String titel;
    String omschrijving;
    double prijs;
    //List media;


    private int ownerId;

}



