package nl.thom.marktplaats.daos;

import nl.thom.marktplaats.domain.Gebruiker;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.temporal.TemporalQuery;
import java.util.List;

@Singleton
public class GebruikerDao extends Dao<Gebruiker, Integer> {

//    @Inject
    private final EntityManager em;

    @Inject
    private Logger log;

    @Inject
    public GebruikerDao(EntityManager em) {
        this.em = em;
    }

    public Gebruiker getUserByUsernameAndPassword(String username, String password) {
        TypedQuery<Gebruiker> query = em.createNamedQuery("Gebruiker.findByUsernameAndPassword", Gebruiker.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Gebruiker> resultList = query.getResultList();
        return (resultList.size() != 0?resultList.get(0):Gebruiker.builder().build());

    }

}
