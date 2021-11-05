package nl.thom.marktplaats.daos;

import nl.thom.marktplaats.domain.Categorie;

import javax.persistence.TypedQuery;
import java.util.List;

public class CategorieDao extends Dao<Categorie, Integer> {

    public List<Categorie> findAll() {
        return em.createNamedQuery("Categorie.findAll", Categorie.class).getResultList();
    }
}
