package spp.tasksloggingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spp.tasksloggingapi.model.SubTask;

import java.util.List;

public interface SubTaskRepository extends JpaRepository<SubTask, Integer> {

    @Query("SELECT st FROM SubTask st WHERE UPPER(st.subTaskName) LIKE UPPER(CONCAT('%', :subTaskName, '%'))")
    List<SubTask> getSubTaskByName (@Param("subTaskName") String subTaskName);

    @Query("SELECT st FROM SubTask st WHERE st.subTaskAssignee.personId = :assigneeId")
    List<SubTask> getSubTaskByAssigneeId(@Param("assigneeId") Integer assigneeId);

    @Query("SELECT st FROM SubTask st WHERE st.isSubTaskComplete = :status")
    List<SubTask> getSubTaskByStatus(@Param("status") boolean status);
}
