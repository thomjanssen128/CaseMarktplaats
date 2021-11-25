package nl.thom.rest.domain;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
// @XmlRootElement
public class Contact {
    private Long id;
    private String firstName;
    private String surname;
    private String email;
}
