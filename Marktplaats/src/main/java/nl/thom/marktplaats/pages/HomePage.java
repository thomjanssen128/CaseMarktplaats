package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.Backdoor;
import nl.thom.marktplaats.User;

import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.App.prompt;

public class HomePage extends Page {


    private final RegistrationPage registrationPage;
    private final InlogPage inlogPage;
    public static User currentUser;
    private boolean running = true;

    List<String> options1 = Arrays.asList(
            "Welkom bij Marktplaats wat wil je doen?",
            "Inloggen",
            "Registreren",
            "Stoppen");
    List<String> options2 = Arrays.asList(
            "Welkom bij Marktplaats wat wil je doen?",
            "Uitloggen",
            "Advertentie plaatsen",
            "Mijn advertenties bekijken",
            "Stoppen");

    public HomePage() {
        this.registrationPage = new RegistrationPage();
        this.inlogPage = new InlogPage();
        this.currentUser = null;
    }

    @Override
    public void render() {
        while (running) {
            header();
            if (currentUser == null) {
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
                case "?":
                    Backdoor.open();
                    break;
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
                    System.out.println("Uitloggen");
                    currentUser = null;
                    break;
                case "2":
                    System.out.println("Advertentie plaatsen");
                    //registrationPage.render();
                    break;
                case "3":
                    System.out.println("Advertentie bekijken");
                    //registrationPage.render();
                    break;
                case "x":
                    System.out.println("Tot ziens!");
                    running = false;
                    return;
                case "?":
                    Backdoor.open();
                default:
                    System.out.println(MAAKKEUZE);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


