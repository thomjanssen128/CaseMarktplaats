package nl.thom.marktplaats.daos;

import nl.thom.marktplaats.domain.Advertentie;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;

@Singleton
public class AdvertentieDao extends Dao<Advertentie, Integer> {

    private final EntityManager em;

    @Inject
    public AdvertentieDao(EntityManager em) {
        this.em = em;
    }

    public List<Advertentie> getAllAdsOfUserById(int id) {
        return em.createNamedQuery("Advertentie.getAdsFromUserById", Advertentie.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Advertentie> getAdByTitleAndPriceAndUserId(String title, Double price, int id) {
        List<Advertentie> ads = em.createNamedQuery("Advertentie.getAdByTitleAndPriceAndUserId", Advertentie.class)
                .setParameter("title", title)
                .setParameter("price", price)
                .setParameter("id", id)
                .getResultList();
        return ads;
    }
}
