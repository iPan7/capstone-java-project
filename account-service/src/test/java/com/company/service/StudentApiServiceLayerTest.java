package com.company.service;

import com.company.model.Student;
import com.company.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class StudentApiServiceLayerTest {

    @Autowired
    private StudentApiServiceLayer service;

    private Student Ken;
    private Student Ryu;
    private List<Student> streetFighterStudents;

    @Before
    public void setUp() throws Exception {

        StudentRepository mockRepo = mock(StudentRepository.class);

        Ken = new Student(1, 1, "Ken", "Rogers", "I love meatballs");
        Ryu = new Student(2, 1, "Ryu", "Baka", "I do mean kicks!");
        Student inputRyu = new Student(1, "Ryu", "Baka", "I do mean kicks!");

        streetFighterStudents = new ArrayList<Student>();
        streetFighterStudents.add(Ken);
        streetFighterStudents.add(Ryu);

        doReturn(Ken).when(mockRepo).getById(1);
        doReturn(streetFighterStudents).when(mockRepo).findAll();
        doReturn(Ryu).when(mockRepo).save(inputRyu);

        service = new StudentApiServiceLayer(mockRepo);
    }

    @Test
    public void shouldReturnAllStudents() {
        List<Student> expectedOutput = new ArrayList<>();
        expectedOutput.add(Ken);
        expectedOutput.add(Ryu);

        List<Student> actualOutput = service.getAllStudents();

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void shouldReturnInstructorById() {
        Student expectedOutput = Ken;
        Student actualOutput = service.getStudentById(1);
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void shouldReturnInstructorOnCreation() {
        Student inputRyu = new Student(1, "Ryu", "Baka", "I do mean kicks!");
        Student actualOutput = service.createStudent(inputRyu);
        assertEquals(Ryu, actualOutput);
    }
}