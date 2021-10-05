package nl.thom.marktplaats;

import nl.thom.marktplaats.pages.HomePage;
import nl.thom.marktplaats.util.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static List<User> users = new ArrayList<>();

    private static final Scanner scanner = new Scanner(System.in);

    public static String readLine() {
        return scanner.nextLine();
    }
    public static String prompt(String message){
        System.out.print(message);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        // HomePage homepage = new HomePage();
        // homepage.render();
        PasswordGenerator p = new PasswordGenerator();
        System.out.println(p.generator());

    }
}
