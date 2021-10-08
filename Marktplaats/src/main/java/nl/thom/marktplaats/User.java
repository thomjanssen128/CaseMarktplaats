package nl.thom.marktplaats;


public class User {
    public String name;
    String email;
    String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User: " + name + ", " + email + ", " + password;
    }
}