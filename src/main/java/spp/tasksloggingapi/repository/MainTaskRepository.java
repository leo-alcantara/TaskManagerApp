package spp.tasksloggingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spp.tasksloggingapi.model.MainTask;

import java.util.List;

public interface MainTaskRepository extends JpaRepository<MainTask, Integer> {

    @Query("SELECT t FROM MainTask t WHERE UPPER(t.mainTaskName) LIKE UPPER(CONCAT('%', :mainTaskName, '%'))")
    List<MainTask> getMainTasksByName(@Param("mainTaskName") String mainTaskName);

    @Query("SELECT t FROM MainTask t WHERE t.taskGroup = :taskGroup")
    List<MainTask> getMainTasksByTaskGroup(@Param("taskGroup") MainTask.TaskGroups taskGroup);

    @Query("SELECT t FROM MainTask t WHERE t.isTaskComplete = :status")
    List<MainTask> getMainTasksByStatus(@Param("status") boolean status);

    @Query("SELECT t FROM MainTask t JOIN Person p ON t.taskAssignee.personId = p.personId " +
            "WHERE t.taskAssignee.personId = :assigneeId")
    List<MainTask> getMainTasksByAssigneeId(@Param("assigneeId") Integer assigneeId);
}
