package spp.jpaimplementation.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Entity
public class MainTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_task_id", nullable = false)
    private Integer mainTaskId;
    private String mainTaskName;
    private Time timeSpentOnTask;
    private TaskGroups taskGroup;

    @OneToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "mainTask")
    private List<SubTask> subTasks;

    private boolean isTaskComplete;

    @ManyToOne(cascade = {CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "task_assignee_person_id")
    private Person taskAssignee;

    public MainTask() {
    }

    public MainTask(String mainTaskName, TaskGroups taskGroup,
                    Person taskAssignee, List<SubTask> subTasks,
                    boolean isTaskComplete) {
        this.mainTaskName = mainTaskName;
        this.taskGroup = taskGroup;
        this.taskAssignee = taskAssignee;
        this.subTasks = subTasks;
        this.isTaskComplete = isTaskComplete;
    }

    public Integer getMainTaskId() {
        return mainTaskId;
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

    public TaskGroups getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(TaskGroups taskGroups) {
        this.taskGroup = taskGroups;
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
        for (SubTask subTask : subTasks) {
            this.isTaskComplete = subTask.isSubTaskComplete();
        }
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
        MainTask mainTask = (MainTask) o;
        return isTaskComplete() == mainTask.isTaskComplete() && Objects.equals(getMainTaskId(), mainTask.getMainTaskId()) && Objects.equals(getMainTaskName(), mainTask.getMainTaskName()) && Objects.equals(getTimeSpentOnTask(), mainTask.getTimeSpentOnTask()) && getTaskGroup() == mainTask.getTaskGroup();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMainTaskId(), getMainTaskName(), getTimeSpentOnTask(), getTaskGroup(), isTaskComplete());
    }

    @Override
    public String toString() {
        return "MainTask{" +
                "id=" + mainTaskId +
                ", mainTaskName='" + mainTaskName + '\'' +
                ", timeSpentOnTask=" + timeSpentOnTask +
                ", taskGroups=" + taskGroup +
                ", isTaskComplete=" + isTaskComplete +
                '}';
    }

    public enum TaskGroups {

        KITCHEN("Kitchen"),
        HOUSE_KEEPING("House Keeping"),
        FRONT_DESK("Front Desk");

        public final String value;

        TaskGroups(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
