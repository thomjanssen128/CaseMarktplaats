package nl.thom.marktplaats.util;

import nl.thom.marktplaats.daos.AdvertentieDao;
import nl.thom.marktplaats.daos.CategorieDao;
import nl.thom.marktplaats.daos.GebruikerDao;
import nl.thom.marktplaats.domain.Advertentie;
import nl.thom.marktplaats.domain.Categorie;
import nl.thom.marktplaats.domain.Gebruiker;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CreateData {
    @Inject
    private GebruikerDao userDao;
    @Inject
    private CategorieDao catDao;
    @Inject
    private AdvertentieDao adDao;

    static List<Gebruiker> gebruikers = new ArrayList<>();
    static List<Categorie> categories = new ArrayList<>();
    static List<Advertentie> ads = new ArrayList<>();

    public void create() {
        addGebruikers();
        gebruikers.forEach(userDao::save);
        addCats();
        categories.forEach(catDao::save);
        addAds();
        ads.forEach(adDao::save);

        //App.setCurrentUser(gebruikers.get(0));
    }

    private static void addGebruikers() {
        Gebruiker u = Gebruiker.builder().username("Thom").email("thom@hoi.nl").password("1234").build();
        Gebruiker o = Gebruiker.builder().username("Ollie").email("ollie@hoi.nl").password("mauw").build();
        Gebruiker b = Gebruiker.builder().username("Bella").email("bella@cat.mauw").password("iloveollie").build();
        Gebruiker l = Gebruiker.builder().username("Lotte").email("lot@cat.mauw").password("ilovebella").build();
        o.setObeyRules(true);
        o.setBezorgwijzen(0b100 | 0b001);
        gebruikers.add(u);
        gebruikers.add(o);
        gebruikers.add(b);
        gebruikers.add(l);
    }

    private static void addCats() {
        List<String> cats = List.of("Dieren", "Meubels", "It-Spullen", "Seizoensartikelen", "Anders");
        cats.stream()
                .map(c -> Categorie.builder().naam(c).build())
                .forEach(c -> categories.add(c));
    }

    private static void addAds() {
        ads.add((Advertentie.builder().titel("Stoel").prijs(23.50d).categorie(categories.get(1)).owner(gebruikers.get(0)).build()));
        ads.add((Advertentie.builder().titel("Jaapie").prijs(0d).categorie(categories.get(0)).owner(gebruikers.get(0)).build()));
        ads.add((Advertentie.builder().titel("Poes").prijs(120d).categorie(categories.get(0)).owner(gebruikers.get(3)).build()));
        ads.add((Advertentie.builder().titel("NES").prijs(1234567).categorie(categories.get(4)).owner(gebruikers.get(2)).build()));
        ads.add((Advertentie.builder().titel("Muis").prijs(12d).categorie(categories.get(2)).owner(gebruikers.get(0)).build()));
        ads.add((Advertentie.builder().titel("OCP-boek").prijs(80d).categorie(categories.get(2)).owner(gebruikers.get(0)).build()));
        ads.add((Advertentie.builder().titel("Gedicht").prijs(1.25d).categorie(categories.get(4)).owner(gebruikers.get(1)).build()));
    }
}
