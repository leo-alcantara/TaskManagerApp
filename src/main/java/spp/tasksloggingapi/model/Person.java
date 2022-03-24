package spp.tasksloggingapi.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private Integer personId;
    private String personName;
    private String personPhoneNumber;

    @OneToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
    }, fetch = FetchType.LAZY,
            mappedBy = "taskAssignee")
    private List<MainTask> assignedTasks;


    public Person(String personName, String personPhoneNumber, List<MainTask> assignedTasks) {
        this.personName = personName;
        this.personPhoneNumber = personPhoneNumber;
        this.assignedTasks = assignedTasks;
    }

    public Person() {

    }

    public Integer getPersonId() {
        return personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setPersonPhoneNumber(String personPhoneNumber) {
        this.personPhoneNumber = personPhoneNumber;
    }

    public List<MainTask> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<MainTask> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getPersonId(), person.getPersonId()) && Objects.equals(getPersonName(), person.getPersonName()) && Objects.equals(getPersonPhoneNumber(), person.getPersonPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonId(), getPersonName(), getPersonPhoneNumber());
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", personName='" + personName + '\'' +
                ", personPhoneNumber='" + personPhoneNumber + '\'' +
                '}';
    }
}
