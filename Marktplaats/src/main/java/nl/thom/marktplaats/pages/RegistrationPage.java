package nl.thom.marktplaats.pages;

import java.util.Arrays;
import java.util.List;

public class RegistrationPage extends Page {

    // singleton design pattern begin -----------
    // private static RegistrationPage self;
    //
    // private RegistrationPage() {}
    //
    // public static synchronized RegistrationPage registrationpage() {
    //     if (self == null) self = new RegistrationPage();
    //     return self;
    // }
    // singleton design pattern end -------------

    static List<String> options = Arrays.asList("We gaan registreren!", "Yes!", "Cancel");

    @Override
    public void render() {
        header();
        renderMenu(options);

    }

    public void renderPage() {
        render();

    }

}
