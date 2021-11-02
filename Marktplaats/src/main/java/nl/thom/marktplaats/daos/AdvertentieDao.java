package nl.thom.marktplaats.daos;

import nl.thom.marktplaats.domain.Advertentie;
import nl.thom.marktplaats.domain.Categorie;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

@Singleton
public class AdvertentieDao extends Dao<Advertentie, Integer> {

    private final EntityManager em;

    @Inject
    public AdvertentieDao(EntityManager em) {
        this.em = em;
    }

}
