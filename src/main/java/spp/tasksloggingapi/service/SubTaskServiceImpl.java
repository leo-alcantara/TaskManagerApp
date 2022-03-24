package spp.tasksloggingapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import spp.tasksloggingapi.Exceptions.ResourceNotFoundException;
import spp.tasksloggingapi.model.MainTask;
import spp.tasksloggingapi.model.SubTask;
import spp.tasksloggingapi.repository.SubTaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubTaskServiceImpl {

    private final SubTaskRepository subTaskRepository;

    @Autowired
    public SubTaskServiceImpl(SubTaskRepository subTaskRepository) {
        this.subTaskRepository = subTaskRepository;
    }

    public SubTask insertSubTask(SubTask subTask) {
        subTaskRepository.save(subTask);
        return subTask;
    }

    public SubTask getSubTaskById(Integer subTaskId) {
        Optional<SubTask> foundSubTask = subTaskRepository.findById(subTaskId);

        if (foundSubTask.isPresent()) {
            return foundSubTask.get();
        } else {
            throw new ResourceNotFoundException("Could not find Sub Task with id " + subTaskId);
        }
    }

    public List<SubTask> getAllSubTasks() {
        return subTaskRepository.findAll();
    }

    public SubTask updateSubTask(Integer subTaskToBeUpdatedId, SubTask subTaskToUpdateTo) {
        Optional<SubTask> toBeUpdated = subTaskRepository.findById(subTaskToBeUpdatedId);

        if (toBeUpdated.isPresent()) {
            toBeUpdated.get().setSubTaskName(subTaskToUpdateTo.getSubTaskName());
            toBeUpdated.get().setSubTaskComplete(subTaskToUpdateTo.isSubTaskComplete());
            toBeUpdated.get().setSubTaskAssignee(subTaskToUpdateTo.getSubTaskAssignee());
            return toBeUpdated.get();
        } else {
            throw new ResourceNotFoundException("Could not update Sub Task with id " + subTaskToBeUpdatedId + " because Sub Task does not exist.");
        }
    }

    public void deleteSubTaskById(Integer subTaskId) {
        subTaskRepository.deleteById(subTaskId);
    }

    public List<SubTask> getSubTaskByName (String subTaskName) {
        return subTaskRepository.getSubTaskByName(subTaskName);
    }

    public List<SubTask> getSubTaskByAssigneeId(Integer assigneeId) {
        return subTaskRepository.getSubTaskByAssigneeId(assigneeId);
    }

    public List<SubTask> getSubTaskByStatus(boolean status) {
        return subTaskRepository.getSubTaskByStatus(status);
    }
}
