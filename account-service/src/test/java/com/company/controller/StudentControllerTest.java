package com.company.controller;

import com.company.model.Student;
import com.company.service.StudentApiServiceLayer;
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

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private StudentApiServiceLayer mockService;

    private Student Ken;
    private Student Ryu;
    private String kenJson;
    private List<Student> allStudents = new ArrayList<>();
    private String allStudentsJson;

    @Before
    public void setup() throws Exception {
        Ken = new Student(1, 1, "Ken", "Rogers", "I love meatballs");
        Ryu = new Student(2, 1, "Ryu", "Baka", "I do mean kicks!");

        kenJson = mapper.writeValueAsString(Ken);

        allStudents.add(Ken);
        allStudents.add(Ryu);

        allStudentsJson = mapper.writeValueAsString(allStudents);
    }

    @Test
    public void shouldReturn422WithInvalidBody() throws Exception {

        Student inputStudent = new Student();
        inputStudent.setFirstName("Bob");
        inputStudent.setStudentBio("This is my life.");
        String inputJson = mapper.writeValueAsString(inputStudent);

        mockMvc.perform(
                post("/student").content(inputJson).contentType(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldBeStatus200ForNonExistentStudentId() throws Exception {
        given(mockService.getStudentById(-1)).willReturn(null);
        mockMvc.perform(get("/student/-1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAllStudentsInCollection() throws Exception {
        given(mockService.getAllStudents()).willReturn(allStudents);

        mockMvc.perform(get("/student"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allStudentsJson));
    }

    @Test
    public void shouldReturnStudentById() throws Exception {
        given(mockService.getStudentById(33)).willReturn(Ken);

        mockMvc.perform(
                get("/student/33")
        ).andExpect(status().isOk()).andExpect(content().json(kenJson));
    }

    @Test
    public void shouldCreateStudentOnPostRequest() throws Exception {
        Student Bison = new Student(1, "Bison", "Mare", "This is Bison story.");
        Student expectedBison = new Student(1, "Bison", "Mare", "This is Bison story.");
        String inputBison = mapper.writeValueAsString(Bison);
        String outputBison = mapper.writeValueAsString(expectedBison);

        given(mockService.createStudent(Bison)).willReturn(expectedBison);

        mockMvc.perform(post("/student").content(inputBison).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpect(content().json(outputBison));
    }

    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(
                put("/student/1")
                        .content(kenJson).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(
                delete("/student/1")
        ).andExpect(status().isOk());
    }
}

