package nl.thom.marktplaats.pages;

import nl.thom.marktplaats.daos.AdvertentieDao;
import nl.thom.marktplaats.daos.GebruikerDao;

import javax.inject.Inject;

public class DeleteAdPage extends Page{
    @Inject
    AdvertentieDao adDao;

    public void render() {

        header();

        // print all ads

        // make choice which to del

        // sure?

        // del
    }


}
