package nl.thom.marktplaats.util;

import org.junit.jupiter.api.Test;

import static nl.thom.marktplaats.util.Util.*;
import static org.junit.jupiter.api.Assertions.*;

class UtilTest {
    @Test
    public void colourfulText() {
        printR("Rood");
        printG("Groen");
        printB("Blauw");
        printC("Cyan");
        printM("Magenta");
        printY("Geel");

    }

}