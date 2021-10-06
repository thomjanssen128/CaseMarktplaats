package nl.thom.marktplaats;

import nl.thom.marktplaats.pages.HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LoginUser {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private User user;
    private User nullUser = new User(null,null,null);

    static List<User> users = App.users;


    //
    // public void setUser(User user) {
    //     this.user = user;
    // }

    private void login(User user) {
        HomePage.currentUser = user;
    }


    public void validateCredentials(String name, String password) {
        user = getUser(name, password);
        System.out.println("INGELOGD: " + user);
        login(user);
    }

    public User getUser(String name, String password) {
        for (User user : users) {
            System.out.println(user.name+ " " + user.password);
            if (user.name.equals(name) && user.password.equals(password)) {
                return user;
            }
        }
        LOG.warn("Username and/or password are unknown.");
        return nullUser;
    }


}
