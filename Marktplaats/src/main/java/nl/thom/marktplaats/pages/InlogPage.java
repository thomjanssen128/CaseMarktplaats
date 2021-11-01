package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.LoginGebruiker;
import nl.thom.marktplaats.domain.Gebruiker;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.App.prompt;

@Singleton
public class InlogPage extends Page {

    @Inject
    private LoginGebruiker loginGebruiker;

//    InlogPage() {
//        loginUser = new LoginGebruiker();
//    }

    static List<String> options = Arrays.asList("Wil je inloggen?", "Ja", "Nee");

    @Override
    public void render() {
        clearConsole();
        header();
        System.out.println();
        renderMenu(options);
        try {
            switch (prompt(MAAKKEUZE)) {
                case "1":
                    String name = prompt("Gebruikersnaam: ");
                    String password = prompt("Wachtwoord: ");
                    Gebruiker user = loginGebruiker.getUserByUsernameAndPassword(name, password);
                    loginGebruiker.login(user);


                case "x":
                    return;
                default:
                    break;

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void renderPage() {
        render();

    }

}
