package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.Backdoor;
import nl.thom.marktplaats.LoginGebruiker;

import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.App.prompt;

public class InlogPage extends Page {
    private LoginGebruiker loginUser;

    InlogPage() {
        loginUser = new LoginGebruiker();
    }

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
                    loginUser.validateCredentials(name, password);


                case "x":
                    return;
                case "?":
                    Backdoor.open();
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
