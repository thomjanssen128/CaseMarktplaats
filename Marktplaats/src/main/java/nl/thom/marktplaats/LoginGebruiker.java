package nl.thom.marktplaats;

import nl.thom.marktplaats.daos.GebruikerDao;
import nl.thom.marktplaats.domain.Gebruiker;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class LoginGebruiker {

    @Inject
    private Logger log;

    @Inject
    GebruikerDao userDao;

    private Gebruiker user;

    static List<Gebruiker> users = App.gebruikers;

    public void login(Gebruiker gebruiker) {
        App.setCurrentUser(gebruiker);
    }

    public Gebruiker getUserByUsernameAndPassword(String username, String password) {
        Gebruiker user = userDao.getUserByUsernameAndPassword(username, password);
        if (user.getUsername().length() == 0)
            log.warn("Username and/or password are unknown.");
        return user;

        //
        //System.out.println("\033[93mUsername and/or password are unknown.\033[0m");

    }


}
