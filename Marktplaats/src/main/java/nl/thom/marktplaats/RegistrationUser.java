package nl.thom.marktplaats;

public class RegistrationUser {
    public RegistrationUser() { }

    public User register(String name, String email) {
        User user = new User(name, email);
        App.users.add(user);
        return user;
    }
}
