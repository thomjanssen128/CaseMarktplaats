package nl.thom.marktplaats;

import java.util.List;

public class Backdoor {
    static List<Gebruiker> users = App.gebruikers;

    public static void open() {
        for (Gebruiker user : users) {
            System.out.println("\033[31m" + user + "\033[0m");
        }
    }

}
