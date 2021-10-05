package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.RegistrationUser;

import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.App.prompt;

public class RegistrationPage extends Page {

    static List<String> options = Arrays.asList("We gaan registreren!", "Yes!", "Cancel");

    @Override
    public void render() {
        header();
        renderMenu(options);
        try {
            switch (prompt("Doen?")) {
                case "1":
                    String name = prompt("Name:  ");
                    String email = prompt("Email: ");
                    RegistrationUser ru = new RegistrationUser();
                    System.out.println(ru.register(name, email));


                case "x":
                    return;
                default:
                    System.out.println("default");
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
