package fr.checkconsulting.formation122023;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
@Slf4j
public class Exercice extends Personne {


    @PostConstruct
    public void test() {
        Temperature temperature = (a, b) -> a + b;

        List<Personne> personneList = new ArrayList<>();

        Personne p1 = new Personne("chekini", "mahdi",33);
        Personne p2 = new Personne("chekini", "mahdi",33);
        personneList.add(p1);
        personneList.add(p2);

        personneList.stream().filter(p -> p.getAge() > 30).map(p -> p.getNom()).forEach(nom -> log.info(nom));



        log.info("{}", temperature.addition(10, 15));

    }


}
