package nl.thom.marktplaats.util;

import java.util.Random;

public class PasswordGenerator {
    String password="";
    int minChar = 48; // numeral '0'
    int maxChar = 122; // letter 'z'
    int lengthPassword = 3;
    Random r = new Random();

    public String generator(){
        for (int i = 0; i < lengthPassword; i++) {
            password += r.ints(minChar, maxChar)
                    .filter(j -> (j <= 57 || j >= 65) && (j <= 90 || j >= 97))
                    .limit(lengthPassword)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }

        return password;
    }


}
