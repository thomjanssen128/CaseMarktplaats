package nl.thom.marktplaats.util;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class Util {

    public static Scanner scanner;

    public static boolean testmode = false;

    public static void print(String s) {
        System.out.println(s);
    }

    public static final String END = "\033[0m";
    public static final String RED = "\033[91m";
    public static final String GRN = "\033[92m";
    public static final String YEL = "\033[93m";
    public static final String BLU = "\033[94m";
    public static final String MAG = "\033[95m";
    public static final String CYN = "\033[96m";

    public static void printR(String s) { System.out.println(RED + s + END); }

    public static void printG(String s) {
        System.out.println(GRN + s + END);
    }

    public static void printY(String s) {
        System.out.println(YEL + s + END);
    }

    public static void printB(String s) {
        System.out.println(BLU + s + END);
    }

    public static void printM(String s) {
        System.out.println(MAG + s + END);
    }

    public static void printC(String s) {
        System.out.println(CYN + s + END);
    }



    public static String prompt(String message) {
        System.out.print(message);
        return readLine();
    }

    public static String readLine() {
        String line = scanner.hasNextLine() ? scanner.nextLine() : "NO INPUT AVAILABLE";
        if (testmode) {
            print(line);
            sleep(3000);
        }
        return line;
    }

    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public static String prijs(Double s) {
        Currency EURO = java.util.Currency.getInstance("EUR");
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setCurrency(EURO);
        format.setGroupingUsed(false);
        return format.format(s).replace(".", ",");
    }
}
