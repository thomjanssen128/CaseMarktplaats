package nl.thom.marktplaats;

import nl.thom.marktplaats.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Singleton;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

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
        logUitTest();
        logIn();
        showMyAds();

        end();

        setCommands(commands);
        App.main(new String[]{""});
    }

    @Test
    public void logUitTest() {
        addCommands("5");
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
    public void end(){
        addCommands("x", "x", "x");
    }
    // T E S T  U T I L

    private String toLines(List<String> commands) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        commands.forEach(sj::add);

        return sj.toString();
    }

    private void addCommands(String... coms) {
        Arrays.stream(coms).forEach(commands::add);
    }

    private void setCommands(List<String> commands) {
        System.setIn(
                new ByteArrayInputStream(
                        toLines(commands)
                                .getBytes()
                )
        );
    }
}

