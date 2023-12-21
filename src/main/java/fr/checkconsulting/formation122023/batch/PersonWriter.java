package fr.checkconsulting.formation122023.batch;

import fr.checkconsulting.formation122023.entity.Person;
import fr.checkconsulting.formation122023.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class PersonWriter implements ItemWriter<Person> {


    private final PersonRepository personRepository;

    public PersonWriter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {

        log.info("je suis en train d'ecrire les données suivantes {}", chunk.getItems());

        personRepository.saveAll(chunk.getItems());

    }
}
