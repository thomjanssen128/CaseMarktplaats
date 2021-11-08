package nl.thom.marktplaats;

import nl.thom.marktplaats.util.Util;
import nl.thom.marktplaats.util.Util4test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Singleton;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@Singleton
public class AppTest {

    @Mock
    private InputStream in;

    @InjectMocks
    private App app;

    @BeforeEach
    void setUp() {
        Util.testmode = true;
    }

    private List<String> commands = new ArrayList<>();

    @Test
    public void boot() {
        logInFail();
        logIn();
        showMyAds();
        delAd();
        showMyAds();
        delAdFail();
        addAd();
        showMyAds();
        logUitTest();
        regUser();
        loginPeter();
        showMyAds();
        logUitTest();


        end();

        Util4test.setCommands(commands);
        App.main(new String[]{""});
    }

    @Test
    public void logUitTest() {
        addCommands("5");
    }

    @Test
    public void logInFail() {
        addCommands("1", "1", "Thom", "1235");
    }

    @Test
    public void logIn() {
        addCommands("1", "1", "Thom", "1234");
    }

    @Test
    public void showMyAds() {
        addCommands("4");
    }

    @Test
    public void delAd() {
        addCommands("3", "3", "J");
    }

    @Test
    public void delAdFail() {
        addCommands("3", "678");
    }

    @Test
    public void addAd() {
        addCommands("2", "1",
                "Kerstboom \uD83C\uDF84",
                "Mooie boom, wel zelf komen kappen.",
                "20",
                "4");
    }

    @Test
    public void regUser() {
        addCommands("2", "1",
                "Ollie", "Peter",
                "ollie@hoi.nl", "peertje@hoi.nl",
                "N", "J", "J",
                "Loolaan",
                "13b",
                "7301AZ",
                "Apeldoorn",
                "N", "J");
    }

    @Test
    public void loginPeter(){
        addCommands("1", "1", "peter", "JnKm90");
    }
    @Test

    public void end() {
        addCommands("x", "x", "x");
    }

    public void addCommands(String... coms) {
        Arrays.stream(coms).forEach(commands::add);
    }
}

