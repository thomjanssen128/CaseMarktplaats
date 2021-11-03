package nl.thom.marktplaats.daos;

import nl.thom.marktplaats.domain.Advertentie;
import nl.thom.marktplaats.domain.Categorie;
import nl.thom.marktplaats.domain.Categorie;

import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.util.List;


public class CategorieDao extends Dao<Categorie, Integer> {

    public List<String> findAll() {
        TypedQuery<String> query = em.createNamedQuery("Categorie.findAll", String.class);
        return query.getResultList();

    }

}
