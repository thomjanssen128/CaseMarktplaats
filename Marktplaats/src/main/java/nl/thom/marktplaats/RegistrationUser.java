package nl.thom.marktplaats;

public class RegistrationUser {
    public RegistrationUser() { }

    public User register(String name, String email) {
        return new User(name, email);
    }
}
