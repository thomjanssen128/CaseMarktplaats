package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.LoginUser;

import java.util.Arrays;
import java.util.List;

import static nl.thom.marktplaats.App.prompt;

public class InlogPage extends Page{

    private LoginUser loginUser;
    InlogPage() {
        loginUser = new LoginUser();
    }
    static List<String> options = Arrays.asList("Inloggen!", "Yes!", "Cancel");
    @Override
    public void render() {
        header();
        renderMenu(options);
        try {
            switch (prompt("Doen? ")) {
                case "1":
                    String name = prompt("Username:  ");
                    String password = prompt("Password: ");
                    loginUser.validateCredentials(name, password);


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
