package spp.tasksloggingapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spp.tasksloggingapi.model.MainTask;
import spp.tasksloggingapi.model.Person;
import spp.tasksloggingapi.model.SubTask;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SubTaskServiceImplTest {

    @Autowired
    private MainTaskServiceImpl mainTaskService;
    @Autowired
    private PersonServiceImpl personService;
    @Autowired
    private SubTaskServiceImpl subTaskService;

    private MainTask prepareDinner;
    private MainTask receiveGuests;
    private MainTask organiseRooms;

    private Person billy;
    private Person emma;
    private Person sophie;

    private SubTask cleanFridges;
    private SubTask collectGuestsEmails;
    private SubTask prepareStudyRoom;

    private List<MainTask> mainTasks;
    private List<Person> people;
    private List<SubTask> subTasks;

    @BeforeEach
    void setUp() {
        mainTasks = new ArrayList<>();
        people = new ArrayList<>();
        subTasks = new ArrayList<>();

        billy = new Person("Billy", "0798624563", mainTasks);
        personService.insertPerson(billy);
        people.add(billy);
        emma = new Person("Emma", "0756438725", mainTasks);
        personService.insertPerson(emma);
        people.add(emma);
        sophie = new Person("Sophie", "0798653423", mainTasks);
        personService.insertPerson(sophie);
        people.add(sophie);

        prepareDinner = new MainTask("Dinner preparation", MainTask.TaskGroups.KITCHEN, billy, subTasks, false);
        mainTaskService.insertMainTask(prepareDinner);
        mainTasks.add(prepareDinner);
        receiveGuests = new MainTask("Receive guests", MainTask.TaskGroups.FRONT_DESK, emma, subTasks, true);
        mainTaskService.insertMainTask(receiveGuests);
        mainTasks.add(receiveGuests);
        organiseRooms = new MainTask("Organise Rooms", MainTask.TaskGroups.HOUSE_KEEPING, sophie, subTasks, true);
        mainTaskService.insertMainTask(organiseRooms);
        mainTasks.add(organiseRooms);

        cleanFridges = new SubTask("Cleaning", billy, prepareDinner, true);
        subTasks.add(cleanFridges);
        subTaskService.insertSubTask(cleanFridges);

        collectGuestsEmails = new SubTask("Collect emails", emma, receiveGuests, false);
        subTasks.add(collectGuestsEmails);
        subTaskService.insertSubTask(collectGuestsEmails);

        prepareStudyRoom = new SubTask("Organise study rooms", sophie, organiseRooms, true);
        subTasks.add(prepareStudyRoom);
        subTaskService.insertSubTask(prepareStudyRoom);
    }

    @Test
    void should_get_sub_task_by_name_return_list() {
        //Arrange
        List<SubTask> foundSubTasks;
        //Act
        foundSubTasks = subTaskService.getSubTaskByName("Org");
        //Assert
        assertFalse(foundSubTasks.isEmpty());
        assertEquals(1, foundSubTasks.size());
        assertTrue(foundSubTasks.contains(prepareStudyRoom));
        assertEquals(prepareStudyRoom, foundSubTasks.get(0));
    }

    @Test
    void should_get_sub_task_by_assignee_id_return_list() {
        //Arrange
        List<SubTask> foundSubTasks;
        //Act
        foundSubTasks = subTaskService.getSubTaskByAssigneeId(billy.getPersonId());
        //Assert
        assertFalse(foundSubTasks.isEmpty());
        assertEquals(1, foundSubTasks.size());
        assertTrue(foundSubTasks.contains(cleanFridges));
        assertEquals(cleanFridges, foundSubTasks.get(0));
    }

    @Test
    void getSubTaskByStatus() {
        //Arrange
        List<SubTask> foundSubTasks;
        //Act
        foundSubTasks = subTaskService.getSubTaskByStatus(true);
        //Assert
        assertFalse(foundSubTasks.isEmpty());
        assertEquals(2, foundSubTasks.size());
        assertTrue(foundSubTasks.contains(cleanFridges) && subTasks.contains(prepareStudyRoom));
    }
}