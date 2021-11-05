package nl.thom.marktplaats.daos;

import nl.thom.marktplaats.App;
import nl.thom.marktplaats.domain.Advertentie;
import nl.thom.marktplaats.domain.Gebruiker;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Singleton
public class GebruikerDao extends Dao<Gebruiker, Integer> {

    private final EntityManager em;

    @Inject
    public GebruikerDao(EntityManager em) {
        this.em = em;
    }

    public Gebruiker getUserByUsernameAndPassword(String username, String password) {
        try {
            return em.createNamedQuery("Gebruiker.findByUsernameAndPassword", Gebruiker.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult()
                    ;
        } catch (NoResultException nre) {
            //
        }
        return Gebruiker.builder().username("").build();
    }

    public Gebruiker getUserByUsername(String username) {
        try {
            return em.createNamedQuery("Gebruiker.findByUsername", Gebruiker.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException nre) {
            //Ignore this because as per your logic this is ok!
        }
        return App.nullUser;
    }

    public Gebruiker getUserByEmail(String email) {
        try {
            return em.createNamedQuery("Gebruiker.findByEmail", Gebruiker.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException nre) {
            // dont care
        }
        return App.nullUser;
    }

    public List<Advertentie> getAllAdsOfUserById(int id) {
        return em.createNamedQuery("Gebruiker.findAllMyAds", Advertentie.class)
                .setParameter("id", id)
                .getResultList();
    }
}
