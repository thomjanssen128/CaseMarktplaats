package nl.thom.marktplaats;

import nl.thom.marktplaats.daos.GebruikerDao;

public class RegistrationUser {

    private GebruikerDao dao = new GebruikerDao();

    public RegistrationUser() { }

    public void register(String name, String email, String password) {
        Gebruiker user = new Gebruiker(name, email, password);
        App.gebruikers.add(user); // moet dit?
        save(dao, user);
    }

    public void save(GebruikerDao dao, Gebruiker user) {
        dao.save(user);
    }
}
