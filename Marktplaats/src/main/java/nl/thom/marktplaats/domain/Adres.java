package nl.thom.marktplaats.domain;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Adres {
    private String straat;
    private String huisnummer;
    private String postcode;
    private String stad;
}
