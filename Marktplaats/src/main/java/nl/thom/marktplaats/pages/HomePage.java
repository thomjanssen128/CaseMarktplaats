package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.Backdoor;
import nl.thom.marktplaats.Gebruiker;

import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.App.prompt;

public class HomePage extends Page {


    private final AddAdPage addAdPage;
    private final RegistrationPage registrationPage;
    private final InlogPage inlogPage;
    private final ShowAdvertenties showAdvertenties;


    public static Gebruiker currentUser = null;
    private boolean running = true;

    List<String> options1 = Arrays.asList(
            "Welkom bij Marktplaats wat wil je doen?",
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

    public HomePage() {
        this.registrationPage = new RegistrationPage();
        this.inlogPage = new InlogPage();
        this.addAdPage = new AddAdPage();
        this.showAdvertenties = new ShowAdvertenties();
        this.currentUser = null;
    }

    @Override
    public void render() {
        while (running) {
            clearConsole();
            header();
            System.out.println(currentUser == null?"":"\033[94m"+currentUser.name+"\033[0m");

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
                    System.out.println("Account aanpassen");
                    break;
                case "2":
                    System.out.println("Advertentie plaatsen");
                    addAdPage.render();
                    break;
                case "3":
                    System.out.println("Advertentie bekijken");
                    showAdvertenties.show(currentUser.advertenties);
                    break;
                case "4":
                    System.out.println("Uitloggen");
                    currentUser = null;
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


