package com.company.FistToFiveEdgeService.controller;

import com.company.FistToFiveEdgeService.models.Instructor;
import com.company.FistToFiveEdgeService.util.feign.AccountServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private final AccountServiceClient client;

    public InstructorController(AccountServiceClient client) {
        this.client = client;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Instructor> getAllInstructors() {
        return client.getAllInstructors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Instructor getInstructorById(@PathVariable int id) {
        return client.getInstructorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Instructor createInstructor(@RequestBody Instructor instructor) {
        return client.createInstructor(instructor);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInstructor(@PathVariable int id, @RequestBody Instructor instructor) {
        instructor.setId(id);
        client.updateInstructor(id, instructor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInstructor(@PathVariable int id) {
        client.deleteInstructor(id);
    }
}
