package nl.thom.marktplaats;

import nl.thom.marktplaats.daos.GebruikerDao;
import nl.thom.marktplaats.domain.Gebruiker;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import static nl.thom.marktplaats.util.Util.print;

@Singleton
public class LoginGebruiker {

    @Inject
    private Logger log;

    @Inject
    GebruikerDao userDao;

    public void login(Gebruiker gebruiker) {
        App.setCurrentUser(gebruiker);
    }

    public Gebruiker getUserByUsernameAndPassword(String username, String password) {
        Gebruiker user = userDao.getUserByUsernameAndPassword(username, password);
        if (user.getUsername().length() == 0) {
            log.warn("Username and/or password are unknown.");
            print("\033[93mUsername and/or password are unknown.\033[0m");
        }
        return user;

        //

    }
}
