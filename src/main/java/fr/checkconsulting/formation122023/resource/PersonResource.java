package fr.checkconsulting.formation122023.resource;

import fr.checkconsulting.formation122023.aspect.LogCallsNumber;
import fr.checkconsulting.formation122023.dto.PersonDto;
import fr.checkconsulting.formation122023.dto.SearchCriteriaDto;
import fr.checkconsulting.formation122023.dto.SearchPersonDto;
import fr.checkconsulting.formation122023.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/person")
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }


    @LogCallsNumber
    @GetMapping
    public List<PersonDto> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("{id}")
    public PersonDto getPersonById(@PathVariable("id") String email){
        return personService.getPersonById(email);
    }

    @PutMapping("{id}")
    public void updatePerson(@PathVariable("id") String email, @RequestBody PersonDto personDto){
        personService.updatePerson(email, personDto);
    }

    @LogCallsNumber
    @GetMapping("search")
    public List<SearchPersonDto> searchPersonByCriteria(@RequestBody SearchCriteriaDto criteria){
         return personService.searchPersonByCriteria(criteria);
    }

}
