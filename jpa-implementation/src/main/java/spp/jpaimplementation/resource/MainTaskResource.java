package spp.jpaimplementation.resource;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spp.jpaimplementation.dto.MainTaskDto;
import spp.jpaimplementation.model.MainTask;
import spp.jpaimplementation.service.DtoMapper;
import spp.jpaimplementation.service.MainTaskServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mainTask")
public class MainTaskResource {

    private final MainTaskServiceImpl mainTaskService;
    private final DtoMapper mapper;

    @Autowired
    public MainTaskResource(MainTaskServiceImpl mainTaskService, DtoMapper mapper) {
        this.mainTaskService = mainTaskService;
        this.mapper = mapper;
    }

    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "Returns created Main Task Data Transfer Object", response = MainTaskDto.class)
    public ResponseEntity<MainTaskDto> insertMainTask(@RequestBody MainTaskDto mainTaskDto) {
        MainTask insertedMainTask = mainTaskService.insertMainTask(mapper.toMainTask(mainTaskDto));
        MainTaskDto insertedMainTaskDto = mapper.toMainTaskDto(insertedMainTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedMainTaskDto);
    }

    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.GET)
    @ApiOperation(value = "Returns Main Task Data Transfer Object filtered by id", response = MainTaskDto.class)
    public ResponseEntity<MainTaskDto> getMainTaskById(@RequestParam Integer mainTaskId) {
        MainTask mainTaskFilteredById = mainTaskService.getMainTaskById(mainTaskId);
        MainTaskDto mainTaskFilteredByIdDto = mapper.toMainTaskDto(mainTaskFilteredById);
        return ResponseEntity.ok(mainTaskFilteredByIdDto);
    }

    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "Returns All Main Tasks Data Transfer Objects", response = MainTaskDto.class)
    public ResponseEntity<List<MainTaskDto>> getAllMainTasks() {
        List<MainTask> tasks = mainTaskService.getAllMainTasks();
        List<MainTaskDto> tasksDto = tasks
                .stream()
                .map(mapper::toMainTaskDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasksDto);
    }

    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = "Returns Updated Main Task Data Transfer Object", response = MainTaskDto.class)
    public ResponseEntity<MainTaskDto> updateMainTask(Integer mainTaskToUpdateId,
                                                      @RequestBody MainTaskDto mainTaskToUpdateTo) {
        MainTask mainTaskToUpdateToConverted = mainTaskService.updateMainTask(mainTaskToUpdateId, mapper.toMainTask(mainTaskToUpdateTo));
        MainTaskDto mainTaskToUpdateToConvertedDto = mapper.toMainTaskDto(mainTaskToUpdateToConverted);
        return ResponseEntity.ok().body(mainTaskToUpdateToConvertedDto);
    }

    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "Deletes Main Task Data Transfer Objects", response = MainTaskDto.class)
    public ResponseEntity<Void> deleteMainTaskById(@RequestBody Integer mainTaskId) {
        mainTaskService.deleteMainTaskById(mainTaskId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(produces = "application/json", value = "/getByName", method = RequestMethod.GET)
    @ApiOperation(value = "Returns All Main Tasks Data Transfer Objects filtered by name", response = MainTaskDto.class)
    public ResponseEntity<List<MainTaskDto>> getMainTasksByName(@RequestParam String mainTaskName) {
        List<MainTask> tasksFilteredByName = mainTaskService.getMainTasksByName(mainTaskName);
        List<MainTaskDto> tasksFilteredByNameDto = tasksFilteredByName
                .stream()
                .map(mapper::toMainTaskDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasksFilteredByNameDto);
    }

    @RequestMapping(produces = "application/json", value = "/getByGroup", method = RequestMethod.GET)
    @ApiOperation(value = "Returns All Main Tasks Data Transfer Object filtered by group", response = MainTaskDto.class)
    public ResponseEntity<List<MainTaskDto>> getMainTasksByTaskGroup(@RequestParam MainTask.TaskGroups groups) {
        List<MainTask> tasksFilteredByGroups = mainTaskService.getMainTasksByTaskGroup(groups);
        List<MainTaskDto> tasksFilteredByGroupsDto = tasksFilteredByGroups
                .stream()
                .map(mapper::toMainTaskDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasksFilteredByGroupsDto);
    }

    @RequestMapping(produces = "application/json", value = "/getByStatus", method = RequestMethod.GET)
    @ApiOperation(value = "Returns All Main Tasks Data Transfer Object filtered by status", response = MainTaskDto.class)
    public ResponseEntity<List<MainTaskDto>> getMainTasksByStatus(@RequestParam boolean status) {
        List<MainTask> tasksFilteredByStatus = mainTaskService.getMainTasksByStatus(status);
        List<MainTaskDto> tasksFilteredByStatusDto = tasksFilteredByStatus
                .stream()
                .map(mapper::toMainTaskDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasksFilteredByStatusDto);
    }

    @RequestMapping(produces = "application/json", value = "/getByAssignee", method = RequestMethod.GET)
    @ApiOperation(value = "Returns All Main Tasks Data Transfer Object filtered by assignee", response = MainTaskDto.class)
    public ResponseEntity<List<MainTaskDto>> getMainTasksByAssigneeId(@RequestParam Integer assigneeId) {
        List<MainTask> tasksFilteredByAssignee = mainTaskService.getMainTasksByAssigneeId(assigneeId);
        List<MainTaskDto> tasksFilteredByAssigneeDto = tasksFilteredByAssignee
                .stream()
                .map(mapper::toMainTaskDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasksFilteredByAssigneeDto);
    }
}
