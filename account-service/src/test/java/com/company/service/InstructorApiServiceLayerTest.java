package com.company.service;

import com.company.model.Instructor;
import com.company.model.Student;
import com.company.repository.InstructorRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class InstructorApiServiceLayerTest {

    @Autowired
    private InstructorApiServiceLayer service;

    private Student Ken;
    private Student Ryu;
    private Instructor Blanka;
    private Instructor Zangief;
    private Instructor Bison;
    private List<Student> streetFighterStudents;
    private List<Instructor> instructors;

    @Before
    public void setUp() throws Exception {


        InstructorRepository mockRepo = mock(InstructorRepository.class);

        Ken = new Student(1, 1, "Ken", "Rogers", "I love meatballs");
        Ryu = new Student(2, 1, "Ryu", "Baka", "I do mean kicks!");
        streetFighterStudents = new ArrayList<Student>();
        streetFighterStudents.add(Ken);
        streetFighterStudents.add(Ryu);
        Blanka = new Instructor(1, "Blanka", "Zappy", "Git 101", streetFighterStudents);
        Zangief = new Instructor(2, "Zangief", "Hairy", "Git 201", streetFighterStudents);
        Bison = new Instructor(3,"Bison", "M", "Torpedo 101", streetFighterStudents);
        Instructor newBison = new Instructor("Bison", "M", "Torpedo 101", streetFighterStudents);
        instructors = new ArrayList<Instructor>();
        instructors.add(Blanka);
        instructors.add(Zangief);

        doReturn(Blanka).when(mockRepo).getById(1);
        doReturn(instructors).when(mockRepo).findAll();
        doReturn(Bison).when(mockRepo).save(newBison);
        doReturn(Bison).when(mockRepo).save(Blanka);

        service = new InstructorApiServiceLayer(mockRepo);
    }

    @Test
    public void shouldReturnAllInstructors() {
        // Arrange
        List<Instructor> expectedOutput = new ArrayList<Instructor>();
        expectedOutput.add(Blanka);
        expectedOutput.add(Zangief);

        // Act

        List<Instructor> actualOutput = service.getAllInstructors();

        // Assert

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldReturnInstructorById() {
        Instructor expectedOutput = Blanka;
        Instructor actualOutput = service.getInstructorById(1);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldReturnInstructorOnCreation() {

        Instructor inputBison = new Instructor("Bison", "M", "Torpedo 101", streetFighterStudents);
        Instructor actualOutput = service.createInstructor(inputBison);
        assertEquals(Bison, actualOutput);
    }
}