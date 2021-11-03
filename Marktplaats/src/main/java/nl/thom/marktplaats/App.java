package nl.thom.marktplaats;

import com.github.christianspruijt.jwt.JWT;
import nl.thom.marktplaats.daos.AdvertentieDao;
import nl.thom.marktplaats.daos.CategorieDao;
import nl.thom.marktplaats.daos.GebruikerDao;
import nl.thom.marktplaats.domain.Advertentie;
import nl.thom.marktplaats.domain.Categorie;
import nl.thom.marktplaats.domain.Gebruiker;
import nl.thom.marktplaats.pages.HomePage;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Singleton
public class App {

    public static Gebruiker currentUser;
    public static Gebruiker nullUser = Gebruiker.builder().build();

    @Inject
    private Logger log;
    @Inject
    private GebruikerDao userDao;
    @Inject
    private CategorieDao catDao;
    @Inject
    private AdvertentieDao adDao;

    @Inject
    private HomePage homePage;


    static List<Gebruiker> gebruikers = new ArrayList<>();
    static List<Categorie> categories = new ArrayList<>();
    static List<Advertentie> ads = new ArrayList<>();

    private static final Scanner scanner;// = new Scanner(System.in);

    static {
        JWT.awaitReady();
        scanner = new Scanner(System.in);
    }

    public static String readLine() {
        return scanner.nextLine();
    }

    public static String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize();
        App app = weldContainer.select(App.class).get();

        app.boot();

        weld.shutdown();

    }

    // only here for quick tests
    // remove from production
    private static void addGebruikers() {
        Gebruiker u = Gebruiker.builder().username("Thom").email("thom@hoi.nl").password("1234").build();
        Gebruiker o = Gebruiker.builder().username("Ollie").email("ollie@hoi.nl").password("mauw").build();
        Gebruiker b = Gebruiker.builder().username("Bella").email("bella@cat.mauw").password("iloveollie").build();
        o.setObeyRules(true);
        o.setBezorgwijzen(6);
        gebruikers.add(u);
        gebruikers.add(o);
        gebruikers.add(b);
    }

    private static void addCats() {
//        List<String> cats = List.of("Dieren", "Meubels", "IT-Spullen");
//        cats.stream()
//                .map(c -> Categorie.builder().naam(c).build())
//                .forEach(c -> categories.add(c));
        categories.add(Categorie.builder().naam("Dieren").build());
        categories.add(Categorie.builder().naam("Meubels").build());
        categories.add(Categorie.builder().naam("It-troep").build());
        categories.add(Categorie.builder().naam("Kinderen").build());
    }

    private static void addAds() {
        ads.add((Advertentie.builder().titel("Stoel").prijs(23.50d).categorie("2").ownerId(1).build()));
        ads.add((Advertentie.builder().titel("Jaapie").prijs(0d).categorie("4").ownerId(1).build()));
        ads.add((Advertentie.builder().titel("Poes").prijs(120d).categorie("1").ownerId(3).build()));
        ads.add((Advertentie.builder().titel("NES").prijs(1234567).categorie("3").ownerId(2).build()));
        ads.add((Advertentie.builder().titel("Muis").prijs(12d).categorie("3").ownerId(2).build()));
        ads.add((Advertentie.builder().titel("OCP-boek").prijs(80d).categorie("3").ownerId(2).build()));
        ads.add((Advertentie.builder().titel("Gedicht").prijs(1d).categorie("1").ownerId(1).build()));
    }

    public void boot() {

        currentUser = Gebruiker.builder().build(); //nullUser
        addGebruikers();
        addCats();
        addAds();
        gebruikers.forEach(userDao::save);
        categories.forEach(catDao::save);
        ads.forEach(adDao::save);
        currentUser = gebruikers.get(1);
        homePage.render();
    }

    public static Gebruiker getCurrentUser() {
        return currentUser;
    }

    public static Gebruiker setCurrentUser(Gebruiker g) {
        return currentUser = g;
    }
}
