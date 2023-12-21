package fr.checkconsulting.formation122023.batch;

import fr.checkconsulting.formation122023.dto.PersonDto;
import fr.checkconsulting.formation122023.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component("p1")
@Slf4j
public class PersonProcessor implements ItemProcessor<PersonDto, Person> {
    @Override
    public Person process(PersonDto item) throws Exception {

        log.info("je suis en train d'executer le processor 1");
        if ( 3 == 3) throw new Exception("c'est juste pour failer");

        return null;

    }
}
