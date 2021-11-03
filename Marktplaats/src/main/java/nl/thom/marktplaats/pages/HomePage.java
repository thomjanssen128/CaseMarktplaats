package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.App;
import nl.thom.marktplaats.LoginGebruiker;
import nl.thom.marktplaats.domain.Advertentie;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.App.prompt;
import static nl.thom.marktplaats.util.Util.print;

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
    @Inject
    private ShowAdvertenties showAdvertenties;
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
            print("");


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
                    print("Tot ziens!");
                    running = false;
                    return;

                default:
                    print(MAAKKEUZE);
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
                    print("Account aanpassen");
                    break;
                case "2":
                    print("Advertentie plaatsen");
                    addAdPage.render();
                    break;
                case "3":
                    print("Advertentie bekijken");
                    showAdvertenties.show();

                    break;
                case "4":
                    print("Uitloggen");
                    App.setCurrentUser(App.nullUser);
                    break;
                case "x":
                    print("Tot ziens!");
                    running = false;
                    return;
                default:
                    print(MAAKKEUZE);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


