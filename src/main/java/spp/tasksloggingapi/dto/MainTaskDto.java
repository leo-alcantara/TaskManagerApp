package spp.tasksloggingapi.dto;

import spp.tasksloggingapi.model.MainTask;
import spp.tasksloggingapi.model.Person;
import spp.tasksloggingapi.model.SubTask;

import java.sql.Time;
import java.util.List;
import java.util.Objects;

public class MainTaskDto {

    private String mainTaskName;
    private Time timeSpentOnTask;
    private MainTask.TaskGroups taskGroup;
    private List<SubTask> subTasks;
    private boolean isTaskComplete;
    private Person taskAssignee;

    public MainTaskDto(String mainTaskName,
                       MainTask.TaskGroups taskGroup, List<SubTask> subTasks,
                       boolean isTaskComplete, Person taskAssignee) {
        this.mainTaskName = mainTaskName;
        this.taskGroup = taskGroup;
        this.subTasks = subTasks;
        this.isTaskComplete = isTaskComplete;
        this.taskAssignee = taskAssignee;
    }

    public String getMainTaskName() {
        return mainTaskName;
    }

    public void setMainTaskName(String mainTaskName) {
        this.mainTaskName = mainTaskName;
    }

    public Time getTimeSpentOnTask() {
        return timeSpentOnTask;
    }

    public void setTimeSpentOnTask(Time timeSpentOnTask) {
        this.timeSpentOnTask = timeSpentOnTask;
    }

    public MainTask.TaskGroups getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(MainTask.TaskGroups taskGroup) {
        this.taskGroup = taskGroup;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public boolean isTaskComplete() {
        return isTaskComplete;
    }

    public void setTaskComplete(boolean taskComplete) {
        isTaskComplete = taskComplete;
    }

    public Person getTaskAssignee() {
        return taskAssignee;
    }

    public void setTaskAssignee(Person taskAssignee) {
        this.taskAssignee = taskAssignee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainTaskDto that = (MainTaskDto) o;
        return isTaskComplete() == that.isTaskComplete() && Objects.equals(getMainTaskName(), that.getMainTaskName()) && Objects.equals(getTimeSpentOnTask(), that.getTimeSpentOnTask()) && getTaskGroup() == that.getTaskGroup() && Objects.equals(getSubTasks(), that.getSubTasks()) && Objects.equals(getTaskAssignee(), that.getTaskAssignee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMainTaskName(), getTimeSpentOnTask(), getTaskGroup(), getSubTasks(), isTaskComplete(), getTaskAssignee());
    }

    @Override
    public String toString() {
        return "MainTaskDto{" +
                "mainTaskName='" + mainTaskName + '\'' +
                ", timeSpentOnTask=" + timeSpentOnTask +
                ", taskGroups=" + taskGroup +
                ", subTasks=" + subTasks +
                ", isTaskComplete=" + isTaskComplete +
                ", taskAssignee=" + taskAssignee +
                '}';
    }
}
