package nl.thom.marktplaats.pages;

import java.util.List;
import static nl.thom.marktplaats.App.prompt;
public class Page {

    public void render() {
        header();
    }

    public void header() {
        String name = getClass().getSimpleName();
        int length = (40 - name.length()) / 2;
        String line = "* ".repeat(length / 2);
        line += name + " ";
        line += "* ".repeat(length / 2);
        System.out.println(line);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public static void renderMenu(List items) {
        System.out.println(items.get(0));
        int i = 0;

        for (Object item : items.subList(1,items.size())) {
            i++;
            System.out.printf("[%s] %s\n", i == items.size()-1 ? "x" : i, item);
        }
        System.out.println("");

    }
}
