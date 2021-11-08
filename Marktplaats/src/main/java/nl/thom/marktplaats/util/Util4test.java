package nl.thom.marktplaats.util;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.StringJoiner;

public class Util4test {
    // T E S T  U T I L

    public static String toLines(List<String> commands) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        commands.forEach(sj::add);

        return sj.toString();
    }

    public static void setCommands(List<String> commands) {
        System.setIn(
                new ByteArrayInputStream(
                        toLines(commands)
                                .getBytes()
                )
        );
    }
}
