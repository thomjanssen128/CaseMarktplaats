package nl.thom.marktplaats;

import nl.thom.marktplaats.domain.Gebruiker;
import nl.thom.marktplaats.pages.HomePage;
import nl.thom.marktplaats.util.CreateData;
import nl.thom.marktplaats.util.Util;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Scanner;

@Singleton
public class App {

    public static Gebruiker currentUser;
    public static Gebruiker nullUser = Gebruiker.builder().build();

    @Inject
    public CreateData create;

    @Inject
    private HomePage homePage;

//    static {
//        //JWT.awaitReady();
//        Scanner scanner = new Scanner(System.in);
//    }

    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize();
        App app = weldContainer.select(App.class).get();
        app.boot();
        weld.shutdown();
    }

    public void boot() {
        Util.scanner = new Scanner(System.in);
        currentUser = Gebruiker.builder().build(); //nullUser
        create.create();

        homePage.render();
    }

    public static Gebruiker getCurrentUser() {
        return currentUser;
    }

    public static Gebruiker setCurrentUser(Gebruiker g) {
        return currentUser = g;
    }
}
