package com.company.FistToFiveEdgeService.controller;

import com.company.FistToFiveEdgeService.models.Student;
import com.company.FistToFiveEdgeService.util.feign.AccountServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private final AccountServiceClient client;

    public StudentController(AccountServiceClient client) {
        this.client = client;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents() {
        return client.getAllStudents();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentById(@PathVariable int id) {
        return client.getStudentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return client.createStudent(student);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        client.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable int id) {
        client.deleteStudent(id);
    }
}
