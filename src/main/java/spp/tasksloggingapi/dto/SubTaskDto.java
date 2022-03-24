package spp.tasksloggingapi.dto;

import spp.tasksloggingapi.model.Person;

import java.util.Objects;

public class SubTaskDto {

    private String subTaskName;
    private Person subTaskAssignee;
    private boolean isSubTaskComplete;

    public SubTaskDto(String subTaskName, Person subTaskAssignee, boolean isSubTaskComplete) {
        this.subTaskName = subTaskName;
        this.subTaskAssignee = subTaskAssignee;
        this.isSubTaskComplete = isSubTaskComplete;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public Person getSubTaskAssignee() {
        return subTaskAssignee;
    }

    public void setSubTaskAssignee(Person subTaskAssignee) {
        this.subTaskAssignee = subTaskAssignee;
    }

    public boolean isSubTaskComplete() {
        return isSubTaskComplete;
    }

    public void setSubTaskComplete(boolean subTaskComplete) {
        isSubTaskComplete = subTaskComplete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubTaskDto that = (SubTaskDto) o;
        return isSubTaskComplete() == that.isSubTaskComplete() && Objects.equals(getSubTaskName(), that.getSubTaskName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubTaskName(), isSubTaskComplete());
    }

    @Override
    public String toString() {
        return "SubTaskDto{" +
                "subTaskName='" + subTaskName + '\'' +
                ", isSubTaskComplete=" + isSubTaskComplete +
                '}';
    }
}
