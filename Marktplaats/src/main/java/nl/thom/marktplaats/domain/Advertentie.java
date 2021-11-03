package nl.thom.marktplaats.domain;

import lombok.*;
import nl.thom.marktplaats.util.Util;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@NamedQuery(name = "Advertentie.findAll", query = "SELECT a FROM Advertentie a")
@NamedQuery(name = "Advertentie.getAdsFromUserById", query = "SELECT a FROM Advertentie a WHERE a.owner.id = :id")
public class Advertentie extends AbstractEntity implements Identifiable<Integer> {

    @NotNull
    private String titel;

    private String omschrijving;

    @NotNull
    private double prijs;

    @NotNull @ManyToOne
    private Categorie categorie;

    //List media;

    @NotNull @ManyToOne
    private Gebruiker owner;

    @Override
    public String toString() {
        return "Ad: "
                + getId() + " "
                + getTitel() + " "
                + getCategorie() + " "
                + Util.prijs(getPrijs());
    }

}



