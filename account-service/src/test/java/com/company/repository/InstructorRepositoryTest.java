package com.company.repository;

import com.company.model.Instructor;
import com.company.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstructorRepositoryTest {

    @Autowired
    InstructorRepository repo;

    private Student Ken;
    private Student Ryu;
    private List<Student> streetFighterStudents;
    private Instructor Blanka;
    private Instructor Zangief;

    @Before
    public void setUp() {
        repo.deleteAll();

        Ken = new Student(1, 1, "Ken", "Rogers", "I love meatballs");
        Ryu = new Student(2, 1, "Ryu", "Baka", "I do mean kicks!");

        streetFighterStudents = new ArrayList<Student>();
        streetFighterStudents.add(Ken);
        streetFighterStudents.add(Ryu);

        Blanka = new Instructor(1, "Blanka", "Zappy", "Git 101", streetFighterStudents);
        Blanka = repo.save(Blanka);

        Zangief = new Instructor(2, "Zangief", "Hairy", "Git 201", null);
        Zangief = repo.save(Zangief);
    }

        @Test
        public void shouldReturnInstructorById() {
            Instructor result = repo.getById(1);
            assertEquals(Blanka, result);
        }

}