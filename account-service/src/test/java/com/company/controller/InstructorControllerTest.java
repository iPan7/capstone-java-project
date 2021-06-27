package com.company.controller;

import com.company.model.Instructor;
import com.company.model.Student;
import com.company.service.InstructorApiServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(InstructorController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class InstructorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private InstructorApiServiceLayer mockService;

    private Instructor Blanka;
    private Instructor Zangief;
    private String blankaJson;
    private List<Instructor> allInstructors = new ArrayList<>();
    private String allInstructorsJson;

    @Before
    public void setup() throws Exception {
        Student Ken = new Student(1, 1, "Ken", "Rogers", "I love meatballs");
        Student Ryu = new Student(2, 1, "Ryu", "Baka", "I do mean kicks!");
        List<Student> streetFighterStudents = new ArrayList<Student>();
        streetFighterStudents.add(Ken);
        streetFighterStudents.add(Ryu);
        Blanka = new Instructor(1, "Blanka", "Zappy", "Git 101", streetFighterStudents);
        Zangief = new Instructor(2, "Zangief", "Hairy", "Git 201", null);

        blankaJson = mapper.writeValueAsString(Blanka);

        allInstructors.add(Blanka);
        allInstructors.add(Zangief);

        allInstructorsJson = mapper.writeValueAsString(allInstructors);
    }

    @Test
    public void shouldReturn422WithInvalidBody() throws Exception {

        Instructor inputInstructor = new Instructor();
        inputInstructor.setFirstName("Bob");
        inputInstructor.setClassName("Cooking 101");
        String inputJson = mapper.writeValueAsString(inputInstructor);

        mockMvc.perform(
                post("/instructor").content(inputJson).contentType(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldBeStatus200ForNonExistentInstructorId() throws Exception {
        given(mockService.getInstructorById(-1)).willReturn(null);
        mockMvc.perform(get("/instructor/-1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAllInstructorsInCollection() throws Exception {
        given(mockService.getAllInstructors()).willReturn(allInstructors);

        mockMvc.perform(get("/instructor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allInstructorsJson));
    }

    @Test
    public void shouldReturnInstructorById() throws Exception {
        given(mockService.getInstructorById(33)).willReturn(Blanka);

        mockMvc.perform(
                get("/instructor/33")
        ).andExpect(status().isOk()).andExpect(content().json(blankaJson));
    }

    @Test
    public void shouldCreateInstructorOnPostRequest() throws Exception {
        Instructor Bison = new Instructor("Bison", "Max", "Fighting 101", null);
        Instructor expectedBison = new Instructor(1, "Bison", "Max", "Fighting 101", null);
        String inputBison = mapper.writeValueAsString(Bison);
        String outputBison = mapper.writeValueAsString(expectedBison);

        given(mockService.createInstructor(Bison)).willReturn(expectedBison);

        mockMvc.perform(post("/instructor").content(inputBison).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpect(content().json(outputBison));
    }

    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(
                put("/instructor/1")
                        .content(blankaJson).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(
                delete("/instructor/1")
        ).andExpect(status().isOk());
    }
}

