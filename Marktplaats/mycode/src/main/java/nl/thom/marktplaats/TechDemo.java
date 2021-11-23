package nl.thom.marktplaats;

import nl.thom.marktplaats.form.Formulier;
import nl.thom.marktplaats.util.Bezorgwijze;
import nl.thom.marktplaats.util.Util;

import java.util.*;

import static nl.thom.marktplaats.pages.Page.renderMenu;
import static nl.thom.marktplaats.pages.Page.renderMenuOptions;
import static nl.thom.marktplaats.pages.Page.renderMenuOptionsWithCancel;
import static nl.thom.marktplaats.util.Util.prompt;

public class TechDemo {

    private final List<String> items = List.of(
            "Naam",
            "Leeftijd",
            "Gelukkig",
            "Nee"
    );

    public static void main(String[] args) {
        Util.scanner = new Scanner(System.in);
        TechDemo demo = new TechDemo();

        // demo.formDemo();

        // demo.menuDemo();
        // demo.menuOptionsDemo();
        // demo.menuOptionsWithCancelDemo();

        demo.welkeBezorgwijzen();
    }

    private void formDemo() {
        Formulier f = new Formulier();
        f.askForm(items);
        Map<String, String> antwoorden = f.submit();

        items.stream()
                .map(v -> v + " -> " + antwoorden.get(v))
                .forEach(System.out::println);
    }

    private void menuDemo() {
        renderMenu(items);
        String a = prompt("Welke? ");
    }

    private void menuOptionsDemo() {
        renderMenuOptions(items);
        String a = prompt("Welke? ");
    }

    private void menuOptionsWithCancelDemo() {
        renderMenuOptionsWithCancel(items);
        String a = prompt("Welke? ");
    }

    private void welkeBezorgwijzen() {
        List b = Arrays.asList(Bezorgwijze.values());
        boolean aan;
        int mask;

        for (int i = 0; i < 8; i++) {
            mask = 1;
            for (int j = 0; j < 3; j++) {
                aan = (i & mask) != 0;

                System.out.println(i + " " + b.get(j) + " " + aan);

                mask = mask << 1;
            }
        }
    }
}
