package nl.thom.marktplaats.util;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Util {

    public static void print(String s) {
        System.out.println(s);
    }

    public static String prijs (Double s) {
        Currency EURO = java.util.Currency.getInstance("EUR");
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setCurrency(EURO);
        format.setGroupingUsed(false);
        return format.format(s).replace(".", ",");
    }
}
