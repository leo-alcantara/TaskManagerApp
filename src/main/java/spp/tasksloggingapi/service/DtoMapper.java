package spp.tasksloggingapi.service;

import org.springframework.stereotype.Component;
import spp.tasksloggingapi.dto.MainTaskDto;
import spp.tasksloggingapi.dto.PersonDto;
import spp.tasksloggingapi.dto.SubTaskDto;
import spp.tasksloggingapi.model.MainTask;
import spp.tasksloggingapi.model.Person;
import spp.tasksloggingapi.model.SubTask;

@Component
public class DtoMapper {

    public MainTask toMainTask(MainTaskDto mainTaskDto) {
        return new MainTask(mainTaskDto.getMainTaskName(),
                mainTaskDto.getTaskGroup(),
                mainTaskDto.getTaskAssignee(),
                mainTaskDto.getSubTasks(),
                mainTaskDto.isTaskComplete());
    }

    public MainTaskDto toMainTaskDto(MainTask mainTask) {
        return new MainTaskDto(mainTask.getMainTaskName(),
                mainTask.getTaskGroup(),
                mainTask.getSubTasks(),
                mainTask.isTaskComplete(),
                mainTask.getTaskAssignee());
    }

    public Person toPerson(PersonDto personDto) {
        return new Person(personDto.getPersonName(),
                personDto.getPersonPhoneNumber(),
                personDto.getAssignedTasks());
    }

    public PersonDto toPersonDto(Person person) {
        return new PersonDto(person.getPersonName(),
                person.getPersonPhoneNumber(),
                person.getAssignedTasks());
    }

    public SubTask toSubTask(SubTaskDto subTaskDto) {
        return new SubTask(subTaskDto.getSubTaskName(),
                subTaskDto.getSubTaskAssignee(),
                subTaskDto.getMainTask(),
                subTaskDto.isSubTaskComplete());
    }

    public SubTaskDto toSubTaskDto(SubTask subTask) {
        return new SubTaskDto(subTask.getSubTaskName(),
                subTask.getSubTaskAssignee(),
                subTask.getMainTask(),
                subTask.isSubTaskComplete());
    }
}
