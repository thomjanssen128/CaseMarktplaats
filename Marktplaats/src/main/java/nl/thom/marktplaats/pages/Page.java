package nl.thom.marktplaats.pages;

import java.util.List;

import static nl.thom.marktplaats.util.Util.END;
import static nl.thom.marktplaats.util.Util.MAG;
import static nl.thom.marktplaats.util.Util.print;

public abstract class Page {
    public final String MAAKKEUZE = "Maak een keuze: ";
    private static final String MENUKLEUR = "";

    public void render() {
        header();
    }

    public void header() {
        String name = getClass().getSimpleName();
        int length = (40 - name.length()) / 2;
        var line = MAG;
        line += "* ".repeat(length / 2);
        line += name + " ";
        line += "* ".repeat(length / 2);
        line += END;
        print(line);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public static void renderMenu(List items) {
        System.out.println(items.get(0));
        int i = 0;

        for (Object item : items.subList(1, items.size())) {
            i++;
            print(MENUKLEUR + "[" + (i == items.size() - 1 ? "x" : i) + "] " + END + item);
        }
        print("");
    }

    public static void renderMenuOptions(List items) {
        int i = 0;
        for (Object item : items) {
            i++;
            print(MENUKLEUR + "[" + i + "] " + END + item);
        }
    }

    public static void renderMenuOptionsWithCancel(List items) {
        int i = 0;
        for (Object item : items) {
            i++;
            print(MENUKLEUR + "[" + i + "] " + END + item);
        }
        print(MENUKLEUR + "[x]" + END + " Annuleer");
    }
}
