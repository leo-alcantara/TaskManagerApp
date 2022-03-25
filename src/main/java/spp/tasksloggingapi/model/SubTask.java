package spp.tasksloggingapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_task_id", nullable = false)
    private Integer subTaskId;
    private String subTaskName;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_task_assignee_person_id")
    private Person subTaskAssignee;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "main_task_id")
    private MainTask mainTask;
    private boolean isSubTaskComplete;

    public SubTask() {
    }

    public SubTask(String subTaskName, Person subTaskAssignee, MainTask mainTask, boolean isSubTaskComplete) {
        this.subTaskName = subTaskName;
        this.subTaskAssignee = subTaskAssignee;
        this.mainTask = mainTask;
        this.isSubTaskComplete = isSubTaskComplete;
    }

    public Integer getSubTaskId() {
        return subTaskId;
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

    public void setSubTaskId(Integer subTaskId) {
        this.subTaskId = subTaskId;
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
        SubTask subTask = (SubTask) o;
        return isSubTaskComplete() == subTask.isSubTaskComplete() && Objects.equals(getSubTaskId(), subTask.getSubTaskId()) && Objects.equals(getSubTaskName(), subTask.getSubTaskName()) && Objects.equals(getSubTaskAssignee(), subTask.getSubTaskAssignee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubTaskId(), getSubTaskName(), isSubTaskComplete());
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "subTaskId=" + subTaskId +
                ", subTaskName='" + subTaskName + '\'' +
                ", isSubTaskComplete=" + isSubTaskComplete +
                '}';
    }
}
