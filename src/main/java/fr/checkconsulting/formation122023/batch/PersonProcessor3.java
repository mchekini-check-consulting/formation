package fr.checkconsulting.formation122023.batch;

import fr.checkconsulting.formation122023.dto.PersonDto;
import fr.checkconsulting.formation122023.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component("p3")
@Slf4j
public class PersonProcessor3 implements ItemProcessor<PersonDto, Person> {
    @Override
    public Person process(PersonDto item) throws Exception {

        log.info("je suis en train d'executer le processor 3");

        return null;

    }
}
