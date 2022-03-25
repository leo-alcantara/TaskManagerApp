package spp.tasksloggingapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spp.tasksloggingapi.Exceptions.ResourceNotFoundException;
import spp.tasksloggingapi.model.MainTask;
import spp.tasksloggingapi.repository.MainTaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MainTaskServiceImpl {

    private final MainTaskRepository mainTaskRepository;

    @Autowired
    public MainTaskServiceImpl(MainTaskRepository mainTaskRepository) {
        this.mainTaskRepository = mainTaskRepository;
    }

    @Transactional
    public MainTask insertMainTask(MainTask mainTask) {
        mainTaskRepository.save(mainTask);
        return mainTask;
    }

    @Transactional
    public MainTask getMainTaskById(Integer mainTaskId) {
        Optional<MainTask> foundMainTask = mainTaskRepository.findById(mainTaskId);

        if (foundMainTask.isPresent()) {
            return foundMainTask.get();
        } else {
            throw new ResourceNotFoundException("Could not find Main Task with id " + mainTaskId);
        }
    }

    @Transactional
    public List<MainTask> getAllMainTasks() {
        return mainTaskRepository.findAll();
    }

    @Transactional
    public MainTask updateMainTask(Integer mainTaskToBeUpdatedId, MainTask mainTaskToUpdateTo) {
        Optional<MainTask> toBeUpdated = mainTaskRepository.findById(mainTaskToBeUpdatedId);

        if (toBeUpdated.isPresent()) {
            toBeUpdated.get().setMainTaskName(mainTaskToUpdateTo.getMainTaskName());
            toBeUpdated.get().setTaskGroup(mainTaskToUpdateTo.getTaskGroup());
            toBeUpdated.get().setTaskAssignee(mainTaskToUpdateTo.getTaskAssignee());
            toBeUpdated.get().setSubTasks(mainTaskToUpdateTo.getSubTasks());
            toBeUpdated.get().setTaskComplete(mainTaskToUpdateTo.isTaskComplete());
            return toBeUpdated.get();
        } else {
            throw new ResourceNotFoundException("Could not update Main Task with id " + mainTaskToBeUpdatedId + " because Main Task does not exist.");
        }
    }

    @Transactional
    public void deleteMainTaskById(Integer mainTaskId) {
        mainTaskRepository.deleteById(mainTaskId);
    }

    @Transactional
    public List<MainTask> getMainTasksByName(String mainTaskName) {
        return mainTaskRepository.getMainTasksByName(mainTaskName);
    }

    @Transactional
    public List<MainTask> getMainTasksByTaskGroup(MainTask.TaskGroups taskGroup) {
        return mainTaskRepository.getMainTasksByTaskGroup(taskGroup);
    }

    @Transactional
    public List<MainTask> getMainTasksByStatus(boolean status) {
        return mainTaskRepository.getMainTasksByStatus(status);
    }

    @Transactional
    public List<MainTask> getMainTasksByAssigneeId(Integer assigneeId) {
        return mainTaskRepository.getMainTasksByAssigneeId(assigneeId);
    }
}
