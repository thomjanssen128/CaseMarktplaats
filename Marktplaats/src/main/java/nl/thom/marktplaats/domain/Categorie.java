package nl.thom.marktplaats.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity @Builder @AllArgsConstructor @NoArgsConstructor

@NamedQuery(name = "Categorie.findAll",
        query = "SELECT i.naam FROM Categorie i"
)
public class Categorie extends AbstractEntity implements Identifiable<Integer>{
    String naam;
}
