package nl.thom.marktplaats;


import java.util.ArrayList;
import java.util.List;

public class Gebruiker {
    public String name;
    String email;
    String password;
    public List<Advertentie> advertenties = new ArrayList();

    public Gebruiker(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User: " + name + ", " + email + ", " + password;
    }
}
