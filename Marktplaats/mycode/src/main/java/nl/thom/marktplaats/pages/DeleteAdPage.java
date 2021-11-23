package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.App;
import nl.thom.marktplaats.daos.AdvertentieDao;
import nl.thom.marktplaats.daos.GebruikerDao;
import nl.thom.marktplaats.domain.Advertentie;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static nl.thom.marktplaats.util.Util.*;

@Singleton
public class DeleteAdPage extends Page {
    @Inject
    AdvertentieDao adDao;
    @Inject
    private GebruikerDao userDao;

    private boolean geldigeAd = false;

    public void terugNaarhomePage() {
    }

    public void render() {
        List<Advertentie> myAds = userDao.getAllAdsOfUserById(App.currentUser.getId());

        header();

        // print all ads
        renderMenuOptionsWithCancel(myAds);

        // make choice which to del
        String keuze = prompt("Welke advertentie wil je verwijderen? ");

        // klappen we eruit?
        if (keuze.equals("x")) {

        }
        // geldige keuze?
        else if (!(IntStream.range(1, myAds.size() + 1)
                .boxed()
                .map(g -> g.toString())
                .collect(Collectors.toList()).contains(keuze))) {
            // nee!
            printY("Ongeldige keuze!");
            return;
        } else {
            geldigeAd = true;
        }
        if (geldigeAd) {
            int index = Integer.parseInt(keuze) - 1;
            Advertentie ad = myAds.get(index);
            // zeker?
            String zeker = prompt("Weet je zeker dat je " + CYN + ad.getTitel() + END + " wilt verwijderen? J/N? ");
            if (zeker.equals("J")) {
                adDao.remove(ad);
                printY(ad.getTitel() + " is verwijderd.");
            } else {
                printY("Dan blijft ie!");
            }
        }
    }
}
