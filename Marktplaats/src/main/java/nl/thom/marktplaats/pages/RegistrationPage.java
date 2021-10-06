package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.RegistrationUser;
import nl.thom.marktplaats.util.PasswordGenerator;

import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.App.prompt;

public class RegistrationPage extends Page {

    private RegistrationUser registrationUser = new RegistrationUser();

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
                    String password = new PasswordGenerator().generator();
                    registrationUser.register(name, email, password);


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
