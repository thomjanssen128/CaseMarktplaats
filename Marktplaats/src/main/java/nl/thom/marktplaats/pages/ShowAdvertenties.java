package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.Advertentie;
import nl.thom.marktplaats.Gebruiker;

import java.util.List;

public class ShowAdvertenties {
    public void show(List<Advertentie> ads){
        if (ads.size() == 0) {
            System.out.println("Nog geen adverteies");
        }
        for (Advertentie advertentie : ads) {
            System.out.println(advertentie);
        }

    }
}