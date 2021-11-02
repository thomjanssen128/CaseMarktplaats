package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.daos.AdvertentieDao;
import nl.thom.marktplaats.daos.CategorieDao;
import nl.thom.marktplaats.domain.Advertentie;
import nl.thom.marktplaats.form.Formulier;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static nl.thom.marktplaats.App.currentUser;
import static nl.thom.marktplaats.App.prompt;

public class AddAdPage extends Page {
    private Map<String, String> antwoorden;
    static List<String> options = Arrays.asList(
            "Wil je een advertentie plaatsen?",
            "Ja",
            "Nee"
    );

    @Inject
    AdvertentieDao advertentieDao;
    @Inject
    CategorieDao catDao;

    @Override
    public void render() {
        clearConsole();
        header();
        System.out.println();
        renderMenu(options);
        try {
            switch (prompt(MAAKKEUZE)) {
                case "1":
                    askFields();
                    addAd();
                case "x":
                    return;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private Map<String, String> antwoorden() {
        List<String> vragen = List.of("Titel", "Omschrijving", "Prijs");
        Formulier f = new Formulier();
        f.askForm(vragen);
        return f.submit();

    }

    private void askFields() {
        antwoorden = antwoorden();
        renderMenu(catDao.findAll());
    }

    private Advertentie addAd() {

        Advertentie a = Advertentie.builder()
                .titel(antwoorden.get("Titel"))
                .omschrijving(antwoorden.get("Omschrijving"))
                .prijs(Double.parseDouble(antwoorden.get("Prijs")))
                .ownerId(currentUser.getId())
                .build();
        advertentieDao.save(a);
        return a;
    }


}
