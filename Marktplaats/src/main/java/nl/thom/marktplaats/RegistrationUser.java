package nl.thom.marktplaats;

import nl.thom.marktplaats.daos.UserDao;

public class RegistrationUser {

    private UserDao dao = new UserDao();

    public RegistrationUser() { }

    public void register(String name, String email, String password) {
        User user = new User(name, email, password);
        App.users.add(user); // moet dit?
        save(dao, user);
    }

    public void save(UserDao dao, User user) {
        dao.save(user);
    }
}
