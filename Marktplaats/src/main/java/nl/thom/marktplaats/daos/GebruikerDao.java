package nl.thom.marktplaats.daos;

import nl.thom.marktplaats.domain.Advertentie;
import nl.thom.marktplaats.domain.Gebruiker;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;

@Singleton
public class GebruikerDao extends Dao<Gebruiker, Integer> {

    private final EntityManager em;

    @Inject
    public GebruikerDao(EntityManager em) {
        this.em = em;
    }

    public Gebruiker getUserByUsernameAndPassword(String username, String password) {
        return em.createNamedQuery("Gebruiker.findByUsernameAndPassword", Gebruiker.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
    }

    public Gebruiker getUserByUsername(String username) {
        return em.createNamedQuery("Gebruiker.findByUsername", Gebruiker.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public Gebruiker getUserByEmail(String email) {
        return em.createNamedQuery("Gebruiker.findByEmail", Gebruiker.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public List<Advertentie> getAllAdsOfUserById(int id) {
        return em.createNamedQuery("Gebruiker.findAllMyAds", Advertentie.class)
                .setParameter("id", id)
                .getResultList();
    }
}
