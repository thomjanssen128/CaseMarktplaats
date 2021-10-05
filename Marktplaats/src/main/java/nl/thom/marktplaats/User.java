package nl.thom.marktplaats;

public class User {
    String name;
    String email;
    String password;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.password = "";
    }

    @Override
    public String toString() {
        return "User: " + name + " @ " + email;
    }
}
