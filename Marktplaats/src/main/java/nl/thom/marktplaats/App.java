package nl.thom.marktplaats;

import nl.thom.marktplaats.pages.HomePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static List<Gebruiker> gebruikers = new ArrayList<>();

    private static final Scanner scanner = new Scanner(System.in);

    public static String readLine() {
        return scanner.nextLine();
    }

    public static String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        addGebruikers();
        HomePage homepage = new HomePage();
        homepage.render();

    }

    // only here for quick tests
    // remove from production
    private static void addGebruikers() {
        Gebruiker u = new Gebruiker("Thom", "q", "1234");
        Gebruiker o = new Gebruiker("Ollie", "1", "miauw");
        gebruikers.add(u);
        gebruikers.add(o);
    }


}
