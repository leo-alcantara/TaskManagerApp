package spp.tasksloggingapi.dto;

import spp.tasksloggingapi.model.MainTask;
import spp.tasksloggingapi.model.Person;

import java.util.Objects;

public class SubTaskDto {

    private String subTaskName;
    private Person subTaskAssignee;
    private MainTask mainTask;
    private boolean isSubTaskComplete;

    public SubTaskDto(String subTaskName, Person subTaskAssignee, MainTask mainTask, boolean isSubTaskComplete) {
        this.subTaskName = subTaskName;
        this.subTaskAssignee = subTaskAssignee;
        this.mainTask = mainTask;
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

    public MainTask getMainTask() {
        return mainTask;
    }

    public void setMainTask(MainTask mainTask) {
        this.mainTask = mainTask;
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
        return isSubTaskComplete() == that.isSubTaskComplete() && Objects.equals(getSubTaskName(), that.getSubTaskName()) && Objects.equals(getSubTaskAssignee(), that.getSubTaskAssignee()) && Objects.equals(getMainTask(), that.getMainTask());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubTaskName(), getSubTaskAssignee(), getMainTask(), isSubTaskComplete());
    }

    @Override
    public String toString() {
        return "SubTaskDto{" +
                "subTaskName='" + subTaskName + '\'' +
                ", subTaskAssignee=" + subTaskAssignee +
                ", mainTask=" + mainTask +
                ", isSubTaskComplete=" + isSubTaskComplete +
                '}';
    }
}
