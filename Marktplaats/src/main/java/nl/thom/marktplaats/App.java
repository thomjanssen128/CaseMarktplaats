package nl.thom.marktplaats;

import nl.thom.marktplaats.daos.GebruikerDao;
import nl.thom.marktplaats.domain.Gebruiker;
import nl.thom.marktplaats.pages.HomePage;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Singleton
public class App {

    private static Gebruiker currentUser;
    public static Gebruiker nullUser = Gebruiker.builder().build();
    @Inject
    private EntityManager em;
    @Inject
    private Logger log;
    @Inject
    private GebruikerDao userDao;
    @Inject
    private HomePage homePage;



    static List<Gebruiker> gebruikers = new ArrayList<>();

    private static final Scanner scanner = new Scanner(System.in);

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
//        App app = new App();

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
        o.setBezorgwijzen((byte) (0b100 | 0b001));
        gebruikers.add(u);
        gebruikers.add(o);
        gebruikers.add(b);
    }

    private void boot() {
        currentUser = Gebruiker.builder().build();
        addGebruikers();
        gebruikers.forEach(userDao::save);
        homePage.render();
    }

    public static Gebruiker getCurrentUser(){
        return currentUser;
    }
    public static Gebruiker setCurrentUser(Gebruiker g){
        return currentUser = g;
    }
}
