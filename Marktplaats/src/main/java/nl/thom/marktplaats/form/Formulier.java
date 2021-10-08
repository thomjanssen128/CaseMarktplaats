package nl.thom.marktplaats.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.thom.marktplaats.App.prompt;

public class Formulier {
    Map<String, String> antwoorden = new HashMap<String, String>();

    public void askForm(List<String> form) {
        for (String s : form) {
            antwoorden.put(s, prompt(s + ": "));
        }
    }

    public Map<String, String> submit() {
        return antwoorden;
    }

}
