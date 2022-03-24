package spp.tasksloggingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spp.tasksloggingapi.model.MainTask;

import java.util.List;

public interface MainTaskRepository extends JpaRepository<MainTask, Integer> {

    @Query("SELECT t FROM MainTask t WHERE UPPER(t.mainTaskName) LIKE UPPER(CONCAT('%', :mainTaskName, '%'))")
    List<MainTask> getMainTasksByName(@Param("mainTaskName") String mainTaskName);

    @Query("SELECT t FROM MainTask t WHERE UPPER(t.taskGroup) LIKE UPPER(CONCAT('%', :taskGroup, '%'))")
    List<MainTask> getMainTasksByTaskGroup(@Param("taskGroup") MainTask.TaskGroups taskGroup);

    @Query("SELECT t FROM MainTask t WHERE t.isTaskComplete = :status")
    List<MainTask> getMainTasksByStatus(@Param("taskCompleted") boolean status);

    @Query("SELECT t FROM MainTask t WHERE t.taskAssignee.personId = :assigneeId")
    List<MainTask> getMainTasksByAssigneeId(@Param("assigneeId")Integer assigneeId);
}
