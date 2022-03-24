package spp.tasksloggingapi.dto;

import spp.tasksloggingapi.model.MainTask;

import java.sql.Time;
import java.util.List;
import java.util.Objects;

public class MainTaskDto {

    private String mainTaskName;
    private Time timeSpentOnTask;
    private MainTask.TaskGroups taskGroups;
    private List<SubTaskDto> subTasks;
    private boolean isTaskComplete;
    private PersonDto taskAssignee;

    public MainTaskDto(String mainTaskName, Time timeSpentOnTask,
                       MainTask.TaskGroups taskGroups, List<SubTaskDto> subTasks,
                       boolean isTaskComplete, PersonDto taskAssignee) {
        this.mainTaskName = mainTaskName;
        this.timeSpentOnTask = timeSpentOnTask;
        this.taskGroups = taskGroups;
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

    public MainTask.TaskGroups getTaskGroups() {
        return taskGroups;
    }

    public void setTaskGroups(MainTask.TaskGroups taskGroups) {
        this.taskGroups = taskGroups;
    }

    public List<SubTaskDto> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTaskDto> subTasks) {
        this.subTasks = subTasks;
    }

    public boolean isTaskComplete() {
        return isTaskComplete;
    }

    public void setTaskComplete(boolean taskComplete) {
        isTaskComplete = taskComplete;
    }

    public PersonDto getTaskAssignee() {
        return taskAssignee;
    }

    public void setTaskAssignee(PersonDto taskAssignee) {
        this.taskAssignee = taskAssignee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainTaskDto that = (MainTaskDto) o;
        return isTaskComplete() == that.isTaskComplete() && Objects.equals(getMainTaskName(), that.getMainTaskName()) && Objects.equals(getTimeSpentOnTask(), that.getTimeSpentOnTask()) && getTaskGroups() == that.getTaskGroups() && Objects.equals(getSubTasks(), that.getSubTasks()) && Objects.equals(getTaskAssignee(), that.getTaskAssignee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMainTaskName(), getTimeSpentOnTask(), getTaskGroups(), getSubTasks(), isTaskComplete(), getTaskAssignee());
    }

    @Override
    public String toString() {
        return "MainTaskDto{" +
                "mainTaskName='" + mainTaskName + '\'' +
                ", timeSpentOnTask=" + timeSpentOnTask +
                ", taskGroups=" + taskGroups +
                ", subTasks=" + subTasks +
                ", isTaskComplete=" + isTaskComplete +
                ", taskAssignee=" + taskAssignee +
                '}';
    }
}
