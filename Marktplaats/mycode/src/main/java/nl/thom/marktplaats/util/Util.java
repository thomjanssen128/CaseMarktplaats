package nl.thom.marktplaats.util;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.out;

public class Util {

    public static Scanner scanner;

    public static boolean testmode = false;

    public static void print(String s) {
        out.println(s);
    }

    public static void p(String s) {
        out.print(s);
    }

    public static final String END = "\033[0m";
    public static final String RED = "\033[91m";
    public static final String GRN = "\033[92m";
    public static final String YEL = "\033[93m";
    public static final String BLU = "\033[94m";
    public static final String MAG = "\033[95m";
    public static final String CYN = "\033[96m";

    public static void printR(String s) {
        out.println(RED + s + END);
    }

    public static void printG(String s) {
        out.println(GRN + s + END);
    }

    public static void printY(String s) {
        out.println(YEL + s + END);
    }

    public static void printB(String s) {
        out.println(BLU + s + END);
    }

    public static void printM(String s) {
        out.println(MAG + s + END);
    }

    public static void printC(String s) {
        out.println(CYN + s + END);
    }

    public static String prompt(String message) {
        print(message);
        return readLine();
    }

    public static String readLine() {
        String line = scanner.hasNextLine() ? scanner.nextLine() : "NO INPUT AVAILABLE";
        if (testmode) {
            printG(line);
        }
        return line;
    }

    public static String prijs(Double s) {
        Currency EURO = java.util.Currency.getInstance("EUR");
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setCurrency(EURO);
        format.setGroupingUsed(false);
        return format.format(s).replace(".", ",");
    }
}
