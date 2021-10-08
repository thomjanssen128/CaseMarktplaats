package nl.thom.marktplaats;

import java.util.List;

public class Advertentie {
    String titel;
    String omschrijving;
    double prijs;
    List media;

    public static class Builder {
        private String titel;
        private String omschrijving;
        private double prijs;
        private List media;

        public Builder title(String titel) {
            this.titel = titel;
            return this;
        }

        public Builder omschrijving(String omschrijving) {
            this.omschrijving = omschrijving;
            return this;
        }
        public Builder prijs(double omschrijving) {
            this.prijs = prijs;
            return this;
        }
        public Advertentie build() {
            Advertentie advertentie = new Advertentie();
            advertentie.titel = this.titel;
            advertentie.omschrijving = this.omschrijving;
            advertentie.prijs = this.prijs;
            return advertentie;
        }
    }

    private Advertentie() {
    }

    @Override
    public String toString() {
        return "Advertentie{" +
                "titel='" + titel + '\'' +
                ", omschrijving='" + omschrijving + '\'' +
                ", prijs=" + prijs +
                ", media=" + media +
                '}';
    }
}
