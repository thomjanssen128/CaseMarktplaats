package nl.thom.marktplaats.util;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Mailer {

    public void sendMail(String m) {
        System.out.println("Mail send: \033[94m" + m + "\033[0m");
    }

}
