package spp.tasksloggingapi.resource;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spp.tasksloggingapi.dto.PersonDto;
import spp.tasksloggingapi.model.Person;
import spp.tasksloggingapi.service.DtoMapper;
import spp.tasksloggingapi.service.PersonServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonResource {

    private final PersonServiceImpl personService;
    private final DtoMapper mapper;

    @Autowired
    public PersonResource(PersonServiceImpl personService, DtoMapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }

    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "Returns created Person Data Transfer Object", response = Person.class)
    public ResponseEntity<PersonDto> insertPerson(@RequestBody PersonDto personDto) {
        Person insertedPerson = personService.insertPerson(mapper.toPerson(personDto));
        PersonDto insertedPersonDto = mapper.toPersonDto(insertedPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedPersonDto);
    }

    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.GET)
    @ApiOperation(value = "Returns Person Data Transfer Object filtered by Id", response = Person.class)
    public ResponseEntity<PersonDto> getPersonById(@RequestParam Integer personId) {
        Person personFilteredById = personService.getPersonById(personId);
        PersonDto personFilteredByIdDto = mapper.toPersonDto(personFilteredById);
        return ResponseEntity.ok(personFilteredByIdDto);
    }

    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "Returns All Person Data Transfer Objects", response = Person.class)
    public ResponseEntity<List<PersonDto>> getAllPeople() {
        List<Person> people = personService.getAllPeople();
        List<PersonDto> peopleDto = people
                .stream()
                .map(mapper::toPersonDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(peopleDto);
    }

    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = "Returns Updated Person Data Transfer Object", response = Person.class)
    public ResponseEntity<PersonDto> updatePerson(Integer personToBeUpdatedId,
                                                  @RequestBody PersonDto personToUpdateTo) {
        Person personToUpdateToConverted = personService.updatePerson(personToBeUpdatedId, mapper.toPerson(personToUpdateTo));
        PersonDto personToUpdateToConvertedDto = mapper.toPersonDto(personToUpdateToConverted);
        return ResponseEntity.ok().body(personToUpdateToConvertedDto);
    }

    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "Deletes Person Data Transfer Object", response = Person.class)
    public ResponseEntity<Void> deletePersonById(@RequestBody Integer personId) {
        personService.deletePersonById(personId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(produces = "application/json", value = "/getByName", method = RequestMethod.GET)
    @ApiOperation(value = "Returns All Person Data Transfer Object filtered by name", response = Person.class)
    public ResponseEntity<List<PersonDto>> getPersonByName(@RequestParam String personName) {
        List<Person> peopleFilteredByName = personService.getPersonByName(personName);
        List<PersonDto> peopleFilteredByNameDto = peopleFilteredByName
                .stream()
                .map(mapper::toPersonDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(peopleFilteredByNameDto);
    }

    @RequestMapping(produces = "application/json", value = "/getByTask", method = RequestMethod.GET)
    @ApiOperation(value = "Returns All Person Data Transfer Object filtered by assigned task", response = Person.class)
    public ResponseEntity<List<PersonDto>> getPersonByAssignedTaskName(@RequestParam String assignedTaskName) {
        List<Person> peopleFilteredByTask = personService.getPersonByAssignedTaskName(assignedTaskName);
        List<PersonDto> peopleFilteredByTaskDto = peopleFilteredByTask
                .stream()
                .map(mapper::toPersonDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(peopleFilteredByTaskDto);
    }
}
