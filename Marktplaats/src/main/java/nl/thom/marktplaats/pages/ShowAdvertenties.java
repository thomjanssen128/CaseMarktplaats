package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.App;
import nl.thom.marktplaats.daos.GebruikerDao;
import nl.thom.marktplaats.domain.Advertentie;

import javax.inject.Inject;
import java.util.List;

import static nl.thom.marktplaats.util.Util.print;

public class ShowAdvertenties extends Page {

    @Inject
    GebruikerDao userDao;



    public void render() {
        header();
        List<Advertentie> ads = userDao.getAllAdsOfUserById(App.currentUser.getId());
        if (ads.size() == 0) {
            print("\033[93mNog geen advertenties! Kies [2] om een advertentie te plaatsen.\033[0m");
        } else {
            for (Advertentie ad : ads) {
                print(ad.toString());
            }
        }
    }
}


