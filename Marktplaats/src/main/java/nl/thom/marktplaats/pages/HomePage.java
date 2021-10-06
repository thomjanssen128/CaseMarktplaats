package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.User;

import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.App.prompt;

public class HomePage extends Page {

    private final RegistrationPage registrationPage;
    private final InlogPage inlogPage;
    public static User currentUser;

    List<String> options1 = Arrays.asList(
            "Welkom bij Marktplaats wat wil je doen?",
            "Inloggen",
            "Advertentie plaatsen",
            "Registreren",
            "Exit");
    List<String> options2 = Arrays.asList(
            "Welkom bij Marktplaats wat wil je doen?",
            "Uitloggen",
            "Advertentie plaatsen",
            "Mijn advertenties bekijken",
            "Exit");

    public HomePage() {
        this.registrationPage = new RegistrationPage();
        this.inlogPage = new InlogPage();
        this.currentUser = null;
    }

    @Override
    public void render() {
        while (true) {
            header();
            if (currentUser==  null) {
                renderMenu(options1);
            } else {
                renderMenu(options2);
            }

            try {
                switch (prompt("Maak keuze: ")) {
                    case "1":
                        System.out.println("Inloggen");
                        inlogPage.render();
                        break;




                    case "2":
                        System.out.println("AdPl");
                        break;
                    case "3":
                        System.out.println("Reg");
                        registrationPage.render();
                        break;
                    case "x":
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Choose an option!");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}


