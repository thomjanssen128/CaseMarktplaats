package nl.thom.marktplaats.pages;

import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.App.prompt;

public class HomePage extends Page {


    List<String> options1 = Arrays.asList(
            "Welkom bij Marktplaats wat wil je doen?",
            "Inloggen",
            "Advertentie plaatsen",
            "Registreren",
            "Exit");

    @Override
    public void render() {
        while (true) {
            header();
            renderMenu(options1);

            try {
                switch (prompt("Maak keuze: ")) {
                    case "1":
                        System.out.println("Inloggen");
                        break;

                    case "2":
                        System.out.println("AdPl");
                        break;
                    case "3":
                        System.out.println("Reg");
                        RegistrationPage rp = new RegistrationPage();
                        rp.render();
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


