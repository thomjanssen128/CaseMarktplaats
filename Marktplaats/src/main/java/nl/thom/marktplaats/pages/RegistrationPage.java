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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.util.Util.print;
import static nl.thom.marktplaats.util.Util.prompt;

public class RegistrationPage extends Page {

    @Inject
    private RegistreerGebruiker registreerGebruiker;

    @Inject
    private RegistrationService registrationService;

    @Inject
    Logger log;

    @Inject
    private Mailer mailer;

    Adres adres;

    static List<String> options = Arrays.asList("Wil je je registreren?", "Ja", "Nee");

    @Override
    public void render() {
        clearConsole();
        header();
        System.out.println("");

        renderMenu(options);
        try {
            switch (prompt(MAAKKEUZE)) {
                case "1":
                    String username = registreerUsername();
                    String email = registreerEmail();
                    int bezorgwijzen = registreerBezorgwijzen();

                    if (registrationService.bezorgwijzeThuisIsGekozen(bezorgwijzen)) {
                        adres = registreerAdres();
                    } else {
                        adres = Adres.builder().build(); // nullAdres
                    }

                    boolean obeyRules = registreerObeyRules();

                    String password = new PasswordGenerator().generate();

                    Gebruiker g = Gebruiker.builder()
                            .username(username)
                            .email(email)
                            .password(password)
                            .obeyRules(obeyRules)
                            .bezorgwijzen(bezorgwijzen)
                            .adres(adres)
                            .build();

                    registreerGebruiker.registreerGebruiker(g); // of direct dao?

                    mailer.sendMail("Je wachtwoord is: " + g.getPassword());

                case "x":
                    return;
                default:
                    print("default");
                    break;
            }
        } catch (
                Exception e) {
            log.error("Iets ging mis. " + e);
        }
    }

    private String registreerUsername() {
        String username = "";
        boolean notUnique = true;
        while (notUnique) {
            username = prompt("Gebruikersnaam:  ");
            if (registrationService.usernameIsUnique(username)) {
                notUnique = false;
            } else {
                print("\033[94mGebruiksnaam bestaat al! Probeer opnieuw.\033[0m");
            }
        }
        return username;
    }

    private String registreerEmail() {
        String email = "";
        boolean notUnique = true;
        while (notUnique) {
            email = prompt("E-mail:  ");
            if (registrationService.emailIsUnique(email)) {
                notUnique = false;
            } else {
                System.out.println("\033[94mDit e-mailadres is al geregistreerd. Probeer opnieuw.\033[0m");
            }
        }
        return email;
    }

    private int registreerBezorgwijzen() {
        System.out.println("Welke bezorgwijzen ondersteun je?");
        List<String> keuzes = new ArrayList<>();
        for (Bezorgwijze value : Bezorgwijze.values()) {
            keuzes.add(prompt(value + "? J/N  "));
        }
        int bezorgwijzen = 0;
        int index = 0;
        for (String keuze : keuzes) {
            bezorgwijzen = bezorgwijzen << 1;
            if (keuze.contains("J")) bezorgwijzen++;
        }

        return bezorgwijzen;
    }

    private Adres registreerAdres() {
        print("Je koos THUIS, vul nu je adres in.");
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
        return adres;
    }

    private boolean registreerObeyRules() {
        boolean obeyRules = false;
        boolean ok = false;
        while (!ok) {
            String obeyRulesStr = prompt("Houd je je aan de regels? J/N ");
            obeyRules = obeyRulesStr.equals("J");
            if (obeyRules) ok = true;
            else print("\033[93mTja, het moet. Probeer nog 'ns...\033[0m");
        }
        return obeyRules;
    }
}
