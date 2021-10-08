package nl.thom.marktplaats;

import java.util.ArrayList;
import java.util.List;

public class Backdoor {
    static List<User> users = App.users;

    public static void open() {
        for (User user : users) {
            System.out.println(user);
        }
    }

}
