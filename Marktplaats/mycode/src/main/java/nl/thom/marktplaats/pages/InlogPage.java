package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.LoginGebruiker;
import nl.thom.marktplaats.domain.Gebruiker;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

import static nl.thom.marktplaats.util.Util.print;
import static nl.thom.marktplaats.util.Util.prompt;

@Singleton
public class InlogPage extends Page {

    @Inject
    Logger log;
    @Inject
    private LoginGebruiker loginGebruiker;

    static List<String> options = List.of("Wil je inloggen?", "Ja", "Nee");

    @Override
    public void render() {
        header();
        print("");
        renderMenu(options);
        try {
            switch (prompt(MAAKKEUZE)) {
                case "1":
                    String name = prompt("Gebruikersnaam: ");
                    String password = prompt("Wachtwoord: ");
                    Gebruiker user = loginGebruiker.getUserByUsernameAndPassword(name, password);
                    if (!user.getUsername().equals("") && user.getUsername() != null)
                        loginGebruiker.login(user);

                case "x":
                    return;
                default:
                    break;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
