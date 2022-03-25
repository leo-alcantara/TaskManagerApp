package spp.tasksloggingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spp.tasksloggingapi.model.Person;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("SELECT p FROM Person p WHERE UPPER(p.personName) LIKE UPPER(CONCAT('%', :personName, '%'))")
    List<Person> getPersonByName(@Param("personName") String personName);

    @Query("SELECT p FROM Person p JOIN p.assignedTasks AS t WHERE UPPER(t.mainTaskName) LIKE UPPER(CONCAT('%', :taskName, '%'))")
    List<Person> getPersonByAssignedTaskName(@Param("taskName") String taskName);
}
