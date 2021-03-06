package nl.thom.marktplaats.form;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static nl.thom.marktplaats.util.Util.prompt;

public class Formulier {
    private Map<String, String> antwoorden = new HashMap<>();

    public void askForm(Collection<String> form) {
        for (String s : form) {
            antwoorden.put(s, prompt(s + ": "));
        }
    }

    public Map<String, String> submit() {
        return antwoorden;
    }
}
