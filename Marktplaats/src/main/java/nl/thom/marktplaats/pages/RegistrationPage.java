package nl.thom.marktplaats.pages;

//import nl.thom.marktplaats.RegistrationGebruiker;

import nl.thom.marktplaats.RegistreerGebruiker;
import nl.thom.marktplaats.domain.Adres;
import nl.thom.marktplaats.domain.Gebruiker;
import nl.thom.marktplaats.service.RegistrationService;
import nl.thom.marktplaats.util.Bezorgwijze;
import nl.thom.marktplaats.util.Mailer;
import nl.thom.marktplaats.util.PasswordGenerator;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.App.prompt;

public class RegistrationPage extends Page {

    @Inject
    private EntityManager em;// = Persistence.createEntityManagerFactory("MySQL").createEntityManager();

//    @Inject
//    private GebruikerDao userDao;

    @Inject
    private RegistreerGebruiker registreerGebruiker;

    @Inject
    private RegistrationService registrationService;

    @Inject
    Logger log;

    @Inject
    private Mailer mailer;

    static List<String> options = Arrays.asList("Wil je je registreren?", "Ja", "Nee");
    Adres adres;

    @Override
    public void render() {
        clearConsole();
        header();
        System.out.println("");

        renderMenu(options);
        try {
            switch (prompt(MAAKKEUZE)) {
                case "1":
                    String username = "";
                    boolean notUnique = true;
                    while (notUnique) {
                        username = prompt("Gebruikersnaam:  ");
                        if(registrationService.usernameIsUnique(username)) {
                            notUnique = false;
                        } else {
                            System.out.println("\033[94mGebruiksnaam bestaat al! Probeer opnieuw.\033[0m");
                        };
                    };

                    String email = "";
                    notUnique = true;
                    while (notUnique) {
                        email = prompt("E-mail:  ");
                        if(registrationService.emailIsUnique(email)) {
                            notUnique = false;
                        } else {
                            System.out.println("\033[94mDit e-mailadres is al geregistreerd. Probeer opnieuw.\033[0m");
                        };
                    };

                    System.out.println("Welke bezorgwijzen ondersteun je?");
                    List<String> keuzes = new ArrayList<>();
                    for (Bezorgwijze value : Bezorgwijze.values()) {
                        keuzes.add(prompt(value + "? J/N  "));
                    }
                    int bezorgwijzen = 0;
                    int index = 0;
                    for (String keuze : keuzes) {
                        System.out.println(keuze);
                        //bezorgwijzen.append(keuze.contains("J") ? "1" : "0");
                        bezorgwijzen = bezorgwijzen << 1;
                        if (keuze.contains("J")) bezorgwijzen++;

                    }

                    int mask = (bezorgwijzen & 10);
                    if (mask != 0) {
                        // vraag adres
                        System.out.println("Je koos THUIS, vul nu je adres in.");
                        String straat = prompt("Straat: ");
                        String huisnr = prompt("Huisnummer: ");
                        String postcode = prompt("Postcode: ");
                        String gemeente = prompt("Gemeente: ");
                        adres = Adres.builder()
                                .straat(straat)
                                .huisnummer(huisnr)
                                .postcode(postcode)
                                .stad(gemeente)
                                .build();

                    } else {
                        adres = Adres.builder().build();
                    }
                    String obeyRulesStr = prompt("Houd je je aan de regels? J/N ");
                    //TODO: meer uitwerking?

                    boolean obeyRules = obeyRulesStr.equals("J");
                    String password = new PasswordGenerator().generator();


                    Gebruiker g = Gebruiker.builder()
                            .username(username)
                            .email(email)
                            .password(password)
                            .obeyRules(obeyRules)
                            .bezorgwijzen(bezorgwijzen)
                            .adres(adres)
                            .build();


                    registreerGebruiker.registreerGebruiker(g);
                    mailer.sendMail("Je wachtwoord is: " + g.getPassword());


                case "x":
                    return;
                default:
                    System.out.println("default");
                    break;

            }
        } catch (
                Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void renderPage() {
        render();

    }

}
