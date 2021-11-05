package nl.thom.marktplaats.pages;

import java.util.List;

import static nl.thom.marktplaats.util.Util.print;

public abstract class Page {
    public final String MAAKKEUZE = "Maak een keuze: ";

    public void render() {
        header();
    }

    public void header() {
        String name = getClass().getSimpleName();
        int length = (40 - name.length()) / 2;
        var line = "\033[95m";
        line += "* ".repeat(length / 2);
        line += name + " ";
        line += "* ".repeat(length / 2);
        line += "\033[0m";
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
            print("[" + (i == items.size() - 1 ? "x" : i) + "] " + item);
        }
        print("");
    }

    public static void renderMenuOptions(List items) {
        int i = 0;
        for (Object item : items) {
            i++;
            print("[" + i + "] " + item);
        }
    }

    public static void renderMenuOptionsWithCancel(List items) {
        int i = 0;
        for (Object item : items) {
            i++;
            print("[" + i + "] " + item);
        }
        print("[x] Cancel");
    }


    public final static void clearConsole() {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();

    }
}
