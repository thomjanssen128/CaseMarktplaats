package nl.thom.marktplaats;

import nl.thom.marktplaats.daos.GebruikerDao;
import nl.thom.marktplaats.domain.Gebruiker;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RegistreerGebruiker {
    @Inject
    private GebruikerDao gebruikerDao;

    public void registreerGebruiker(Gebruiker g) {
        gebruikerDao.save(g);
    }

}
