package nl.thom.marktplaats.domain;

import lombok.*;
import nl.thom.marktplaats.util.Util;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

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
    @NotNull
    String titel;
    String omschrijving;
    @NotNull
    double prijs;
    @NotNull
    String categorie;
    //List media;

    @NotNull
    private int ownerId;

    @Override
    public String toString() {
        String s = "Ad: "
                + this.getId() + " "
                + this.getTitel() + " "
                + this.getCategorie() + " "
                + Util.prijs(this.getPrijs());
        return s;
    }

}



