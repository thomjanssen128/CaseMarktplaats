package nl.thom.marktplaats.util;

import javax.inject.Singleton;

import static nl.thom.marktplaats.util.Util.print;

@Singleton
public class Mailer {

    public void sendMail(String m) {
        print("Mail send: \033[94m" + m + "\033[0m");
    }
}
