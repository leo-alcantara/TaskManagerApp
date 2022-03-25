package spp.jpaimplementation.resource;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spp.jpaimplementation.dto.SubTaskDto;
import spp.jpaimplementation.model.SubTask;
import spp.jpaimplementation.service.DtoMapper;
import spp.jpaimplementation.service.SubTaskServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subTask")
public class SubTaskResource {

    private final SubTaskServiceImpl subTaskService;
    private final DtoMapper mapper;

    @Autowired
    public SubTaskResource(SubTaskServiceImpl subTaskService, DtoMapper mapper) {
        this.subTaskService = subTaskService;
        this.mapper = mapper;
    }

    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "Returns created Sub Task Data Transfer Object", response = SubTaskDto.class)
    public ResponseEntity<SubTaskDto> insertSubTask(@RequestBody SubTaskDto subTaskDto) {
        SubTask insertedSubTask = subTaskService.insertSubTask(mapper.toSubTask(subTaskDto));
        SubTaskDto insertedSubTaskDto = mapper.toSubTaskDto(insertedSubTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedSubTaskDto);
    }

    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.GET)
    @ApiOperation(value = "Returns Sub Task Data Transfer Object filtered by id", response = SubTaskDto.class)
    public ResponseEntity<SubTaskDto> getSubTaskById(@RequestParam Integer subTaskId) {
        SubTask subTaskFilteredById = subTaskService.getSubTaskById(subTaskId);
        SubTaskDto subTaskFilteredByIdDto = mapper.toSubTaskDto(subTaskFilteredById);
        return ResponseEntity.ok(subTaskFilteredByIdDto);
    }

    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "Returns All Sub Tasks Data Transfer Objects", response = SubTaskDto.class)
    public ResponseEntity<List<SubTaskDto>> getAllMainTasks() {
        List<SubTask> tasks = subTaskService.getAllSubTasks();
        List<SubTaskDto> tasksDto = tasks
                .stream()
                .map(mapper::toSubTaskDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasksDto);
    }

    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = "Returns Updated Sub Task Data Transfer Object", response = SubTaskDto.class)
    public ResponseEntity<SubTaskDto> updateSubTask(Integer subTaskToUpdateId,
                                                    @RequestBody SubTaskDto subTaskToUpdateTo) {
        SubTask subTaskToUpdateToConverted = subTaskService.updateSubTask(subTaskToUpdateId, mapper.toSubTask(subTaskToUpdateTo));
        SubTaskDto subTaskToUpdateToConvertedDto = mapper.toSubTaskDto(subTaskToUpdateToConverted);
        return ResponseEntity.ok().body(subTaskToUpdateToConvertedDto);
    }

    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "Returns Updated Main Task Data Transfer Object", response = SubTaskDto.class)
    public ResponseEntity<Void> deleteSubTaskById(@RequestParam Integer subTaskId) {
        subTaskService.deleteSubTaskById(subTaskId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(produces = "application/json", value = "/getByName", method = RequestMethod.GET)
    @ApiOperation(value = "Returns All Sub Tasks Data Transfer Objects filtered by name", response = SubTaskDto.class)
    public ResponseEntity<List<SubTaskDto>> getSubTaskByName(@RequestParam String subTaskName) {
        List<SubTask> tasksFilteredByName = subTaskService.getSubTaskByName(subTaskName);
        List<SubTaskDto> tasksFilteredByNameDto = tasksFilteredByName
                .stream()
                .map(mapper::toSubTaskDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasksFilteredByNameDto);
    }

    @RequestMapping(produces = "application/json", value = "/getByAssignee", method = RequestMethod.GET)
    @ApiOperation(value = "Returns All Sub Tasks Data Transfer Objects filtered by Assignee", response = SubTaskDto.class)
    public ResponseEntity<List<SubTaskDto>> getSubTaskByAssigneeId(@RequestParam Integer assigneeId) {
        List<SubTask> tasksFilteredByAssigneeId = subTaskService.getSubTaskByAssigneeId(assigneeId);
        List<SubTaskDto> tasksFilteredByAssigneeIdDto = tasksFilteredByAssigneeId
                .stream()
                .map(mapper::toSubTaskDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasksFilteredByAssigneeIdDto);
    }

    @RequestMapping(produces = "application/json", value = "/getByStatus", method = RequestMethod.GET)
    @ApiOperation(value = "Returns All Sub Tasks Data Transfer Objects filtered by status", response = SubTaskDto.class)
    public ResponseEntity<List<SubTaskDto>> getSubTaskByStatus(@RequestParam boolean status) {
        List<SubTask> tasksFilteredByStatus = subTaskService.getSubTaskByStatus(status);
        List<SubTaskDto> tasksFilteredByStatusDto = tasksFilteredByStatus
                .stream()
                .map(mapper::toSubTaskDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasksFilteredByStatusDto);
    }

}
