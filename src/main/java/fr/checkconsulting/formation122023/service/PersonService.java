package fr.checkconsulting.formation122023.service;

import fr.checkconsulting.formation122023.dto.PersonDto;
import fr.checkconsulting.formation122023.dto.SearchCriteriaDto;
import fr.checkconsulting.formation122023.dto.SearchPersonDto;
import fr.checkconsulting.formation122023.entity.Person;
import fr.checkconsulting.formation122023.entity.Product;
import fr.checkconsulting.formation122023.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDto> getAllPersons() {

        return personRepository.findAll().stream()
                .map(this::mapToPersonDto)
                .toList();
    }

    private PersonDto mapToPersonDto(Person person) {
        return PersonDto.builder()
                .old(person.getOld())
                .email(person.getEmail())
                .lastName(person.getLastName())
                .firstName(person.getFirstName())
                .build();
    }

    public PersonDto getPersonById(String email) {
        return mapToPersonDto(personRepository.findById(email).get());
    }

    public void updatePerson(String email, PersonDto personDto) {
        Person updatedPerson = Person.builder()
                .lastName(personDto.getLastName())
                .firstName(personDto.getFirstName())
                .email(email)
                .old(personDto.getOld())
                .build();
        personRepository.save(updatedPerson);

    }

    public List<SearchPersonDto> searchPersonByCriteria(SearchCriteriaDto criteria) {

        return personRepository.searchPersonByCriteria(criteria.getFirstName(), criteria.getLastName(), criteria.getMinAge(), criteria.getMaxAge())
                .stream().map(this::mapToSearchPersonDto)
                .toList();
    }

    private SearchPersonDto mapToSearchPersonDto(Person person) {

        return SearchPersonDto.builder()
                .email(person.getEmail())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .productNumber(person.getProductList().size())
                .poductsNames(person.getProductList().stream().map(Product::getName).collect(Collectors.joining(" || ")))
                .build();
    }
}
