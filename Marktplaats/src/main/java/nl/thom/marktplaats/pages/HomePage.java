package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.App;
import nl.thom.marktplaats.LoginGebruiker;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.App.prompt;

@Singleton
public class HomePage extends Page {

    @Inject
    private RegistrationPage registrationPage;
    @Inject
    private InlogPage inlogPage;
    @Inject
    private LoginGebruiker loginGebruiker;
    @Inject
    private AddAdPage addAdPage;
    //    private final ShowAdvertenties showAdvertenties;
//
//
    private boolean running = true;

    List<String> options1 = Arrays.asList(
            "Welkom bij Marktplaats. Wat wil je doen?",
            "Inloggen",
            "Registreren",
            "Stoppen");
    List<String> options2 = Arrays.asList(
            "Wat wil je doen?",
            "Account aanpassen",
            "Advertentie plaatsen",
            "Mijn advertenties bekijken",
            "Uitloggen",
            "Stoppen");


    @Override
    public void render() {
        while (running) {
            clearConsole();
            header();
            String cn = App.getCurrentUser().getUsername();
            System.out.println(cn == null ? "" : "\033[94m" + cn + "\033[0m");

            if (App.getCurrentUser().getUsername() == null) {
                renderMenu(options1);
                promptNoLogin();
            } else {
                renderMenu(options2);
                promptWithLogin();
            }
            System.out.println("");


        }

    }

    public void promptNoLogin() {
        try {
            switch (prompt(MAAKKEUZE)) {
                case "1":
                    inlogPage.render();
                    break;
                case "2":
                    registrationPage.render();
                    break;
                case "x":
                    System.out.println("Tot ziens!");
                    running = false;
                    return;

                default:
                    System.out.println(MAAKKEUZE);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void promptWithLogin() {
        try {
            switch (prompt(MAAKKEUZE)) {
                case "1":
                    System.out.println("Account aanpassen");
                    break;
                case "2":
                    System.out.println("Advertentie plaatsen");
                    addAdPage.render();
                    break;
                case "3":
                    System.out.println("Advertentie bekijken");
                    //showAdvertenties.show(currentUser.advertenties);
                    break;
                case "4":
                    System.out.println("Uitloggen");
                    App.setCurrentUser(App.nullUser);
                    break;
                case "x":
                    System.out.println("Tot ziens!");
                    running = false;
                    return;
                default:
                    System.out.println(MAAKKEUZE);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


