package nl.thom.marktplaats.pages;

//import nl.thom.marktplaats.RegistrationGebruiker;

import nl.thom.marktplaats.daos.GebruikerDao;
import nl.thom.marktplaats.domain.Gebruiker;
import nl.thom.marktplaats.util.Bezorgwijze;
import nl.thom.marktplaats.util.Mailer;
import nl.thom.marktplaats.util.PasswordGenerator;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.App.prompt;

public class RegistrationPage extends Page {

    @Inject
    private EntityManager em;// = Persistence.createEntityManagerFactory("MySQL").createEntityManager();

    @Inject
    private GebruikerDao userDao;

    @Inject
    Logger log;

    @Inject
    private Mailer mailer;

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

                    String username = prompt("Gebruikersnaam:  ");
                    // TODO: bestaat de username al?

                    String email = prompt("Email: ");
                    // TODO: valideer deze input

                    System.out.println("Welke bezorgwijzen ondersteun je?");
                    for (Bezorgwijze value : Bezorgwijze.values()){
                        System.out.println(value.ordinal()+1 + " " + value);
                    }
                    String bezorgwijzen = prompt("?");

                    String obeyRulesStr = prompt("Houd je je aan de regels? J/N ");
                    //TODO: meer uitwerking?

                    boolean obeyRules = obeyRulesStr.equals("J");
                    String password = new PasswordGenerator().generator();
                    Gebruiker g = Gebruiker.builder()
                            .username(username)
                            .email(email)
                            .password(password)
                            .obeyRules(obeyRules)
                            .build();
                    userDao.save(g);
                    mailer.sendMail("Je wachtwoord is: " + g.getPassword());


                case "x":
                    return;
                default:
                    System.out.println("default");
                    break;

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void renderPage() {
        render();

    }

}
