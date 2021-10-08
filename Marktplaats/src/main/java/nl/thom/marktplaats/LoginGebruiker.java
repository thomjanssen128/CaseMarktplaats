package nl.thom.marktplaats;

import nl.thom.marktplaats.pages.HomePage;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import java.util.List;

public class LoginGebruiker {
    // private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private Gebruiker user;
    private Gebruiker nullUser = new Gebruiker(null, null, null);

    static List<Gebruiker> users = App.gebruikers;


    //
    // public void setUser(User user) {
    //     this.user = user;
    // }

    private void login(Gebruiker gebruiker) {
        HomePage.currentUser = user;
    }


    public void validateCredentials(String name, String password) {
        user = getUser(name, password);
        if (!(user.name == null)) {
            System.out.println("\033[94mINGELOGD: " + user + "\033[0m");
            login(user);
        }
    }

    public Gebruiker getUser(String name, String password) {
        for (Gebruiker user : users) {
            System.out.println(user.name + " " + user.password);
            if (user.name.equals(name) && user.password.equals(password)) {
                return user;
            }
        }
        //LOG.warn("Username and/or password are unknown.");
        System.out.println("\033[93mUsername and/or password are unknown.\033[0m");
        return nullUser;
    }


}
