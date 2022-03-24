package spp.tasksloggingapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spp.tasksloggingapi.Exceptions.ResourceNotFoundException;
import spp.tasksloggingapi.model.Person;
import spp.tasksloggingapi.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person insertPerson(Person person) {
        personRepository.save(person);
        return person;
    }

    public Person getPersonById(Integer personId) {
        Optional<Person> foundPerson = personRepository.findById(personId);

        if (foundPerson.isPresent()) {
            return foundPerson.get();
        } else {
            throw new ResourceNotFoundException("Could not find Person with id " + personId);
        }
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Person updatePerson(Integer personToBeUpdatedId, Person personToUpdateTo) {
        Optional<Person> toBeUpdated = personRepository.findById(personToBeUpdatedId);

        if (toBeUpdated.isPresent()) {
            toBeUpdated.get().setPersonName(personToUpdateTo.getPersonName());
            toBeUpdated.get().setPersonPhoneNumber(personToUpdateTo.getPersonPhoneNumber());
            toBeUpdated.get().setAssignedTasks(personToUpdateTo.getAssignedTasks());
            return toBeUpdated.get();
        } else {
            throw new ResourceNotFoundException("Could not update Person with id " + personToBeUpdatedId + " because Person does not exist.");
        }
    }

    public void deletePersonById(Integer personId) {
        personRepository.deleteById(personId);
    }

    public List<Person> getPersonByName(String personName) {
        return personRepository.getPersonByName(personName);
    }

    public List<Person> getPersonByAssignedTaskName(String taskName) {
        return personRepository.getPersonByAssignedTaskName(taskName);
    }
}
