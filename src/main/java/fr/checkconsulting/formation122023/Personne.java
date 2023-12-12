package fr.checkconsulting.formation122023;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class Personne {

    private String nom;
    private String prenom;
    private Integer age;



    public final void description(){
        log.info("je suis la description officielle de la classe Personne");
    }
}
