package spp.jpaimplementation.dto;

import spp.jpaimplementation.model.MainTask;

import java.util.List;
import java.util.Objects;

public class PersonDto {

    private String personName;
    private String personPhoneNumber;
    private List<MainTask> assignedTasks;

    public PersonDto(String personName, String personPhoneNumber, List<MainTask> assignedTasks) {
        this.personName = personName;
        this.personPhoneNumber = personPhoneNumber;
        this.assignedTasks = assignedTasks;
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
        PersonDto personDto = (PersonDto) o;
        return Objects.equals(getPersonName(), personDto.getPersonName()) && Objects.equals(getPersonPhoneNumber(), personDto.getPersonPhoneNumber()) && Objects.equals(getAssignedTasks(), personDto.getAssignedTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonName(), getPersonPhoneNumber(), getAssignedTasks());
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "personName='" + personName + '\'' +
                ", personPhoneNumber='" + personPhoneNumber + '\'' +
                ", assignedTasks=" + assignedTasks +
                '}';
    }
}
