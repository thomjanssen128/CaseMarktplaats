package nl.thom.marktplaats.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Embeddable
public class Adres {
    private String straat;
    private String huisnummer;
    private String postcode;
    private String stad;
}
