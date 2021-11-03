package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.App;
import nl.thom.marktplaats.daos.GebruikerDao;
import nl.thom.marktplaats.domain.Advertentie;
import static nl.thom.marktplaats.util.Util.print;
import javax.inject.Inject;
import java.util.List;

public class ShowAdvertenties {
    @Inject
    GebruikerDao userDao;

    public void show() {
        List<Advertentie> ads = userDao.getAllAdsOfUserById(App.currentUser.getId());
        for (Advertentie ad : ads) {
            print(ad.toString());
        }
    }

}
