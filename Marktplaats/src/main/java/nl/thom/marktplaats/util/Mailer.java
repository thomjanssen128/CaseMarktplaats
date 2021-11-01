package nl.thom.marktplaats.util;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Mailer {
    @Inject
    private Logger log;
    public void sendMail(String m) {
        log.info("Mail send: " + m);

    }
}
