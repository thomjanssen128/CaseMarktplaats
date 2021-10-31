package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.Advertentie;
import nl.thom.marktplaats.Backdoor;
import nl.thom.marktplaats.form.Formulier;

import java.util.*;

import static nl.thom.marktplaats.App.prompt;
import static nl.thom.marktplaats.pages.HomePage.currentUser;

public class AddAdPage extends Page {
    static List<String> options = Arrays.asList(
            "Wil je een advertentie plaatsen?",
            "Ja",
            "Nee"
    );

    @Override
    public void render() {
        clearConsole();
        header();
        System.out.println();
        renderMenu(options);
        try {
            switch (prompt(MAAKKEUZE)) {
                case "1":
                    addAd();
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

    private Map<String, String> antwoorden() {
        Set<String> vragen = Set.of("Title", "Omschrijving", "Prijs");
        Formulier f = new Formulier();
        f.askForm(vragen);
        return f.submit();

    }

    private Advertentie addAd() {
        Map<String, String> antwoorden = antwoorden();
        Advertentie a = new Advertentie.Builder()
                .title(antwoorden.get("Titel"))
                .omschrijving(antwoorden.get("Omschrijving"))
                .prijs(Double.parseDouble(antwoorden.get("Prijs")))
                .build();

        currentUser.advertenties.add(a); // hoe anders?
        return a;
    }


}
