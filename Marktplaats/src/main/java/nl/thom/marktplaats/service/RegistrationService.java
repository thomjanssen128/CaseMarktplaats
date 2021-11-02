package nl.thom.marktplaats.service;

import nl.thom.marktplaats.daos.GebruikerDao;
import nl.thom.marktplaats.domain.Gebruiker;

import javax.inject.Inject;

public class RegistrationService {
    @Inject
    GebruikerDao gebruikerDao;

    public boolean usernameIsUnique(String username) {
        Gebruiker g = gebruikerDao.getUserByUsername(username);
        return g.getUsername() == null;
    }

    public boolean emailIsUnique(String email) {
        Gebruiker g = gebruikerDao.getUserByEmail(email);
        return g.getEmail() == null;
    }
}
