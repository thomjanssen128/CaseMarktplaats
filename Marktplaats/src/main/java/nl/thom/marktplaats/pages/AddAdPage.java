package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.daos.AdvertentieDao;
import nl.thom.marktplaats.daos.CategorieDao;
import nl.thom.marktplaats.domain.Advertentie;
import nl.thom.marktplaats.domain.Categorie;
import nl.thom.marktplaats.form.Formulier;

import javax.inject.Inject;
import java.util.*;

import static nl.thom.marktplaats.App.currentUser;
import static nl.thom.marktplaats.util.Util.prompt;

public class AddAdPage extends Page {

    static List<String> options = Arrays.asList(
            "Wil je een advertentie plaatsen?",
            "Ja",
            "Nee"
    );
    private Map<String, String> antwoorden;

    @Inject
    AdvertentieDao advertentieDao;

    @Inject
    CategorieDao catDao;

    private List<Categorie> categorien;

    @Override
    public void render() {
        this.categorien = catDao.findAll(); // eenmaal uit DB halen, daarna vaker gebruiken
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
        List<String> catsMenu = new ArrayList<>();
        renderMenuOptions(categorien);
        String catKeuze = prompt("");
        antwoorden.put("categorie", catKeuze);
    }

    private Advertentie addAd() {

        Advertentie a = Advertentie.builder()
                .titel(antwoorden.get("Titel"))
                .omschrijving(antwoorden.get("Omschrijving"))
                .prijs(Double.parseDouble(antwoorden.get("Prijs")))
                .categorie(categorien.get( Integer.parseInt(antwoorden.get("categorie"))-1 ))
                .owner(currentUser)
                .build();
        advertentieDao.save(a);
        return a;
    }
}
