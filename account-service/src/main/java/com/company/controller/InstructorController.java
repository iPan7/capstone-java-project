package com.company.controller;

import com.company.model.Instructor;
import com.company.service.InstructorApiServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    InstructorApiServiceLayer service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Instructor> getAllInstructors() {
        return service.getAllInstructors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Instructor getInstructorById(@PathVariable int id) {
        return service.getInstructorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Instructor createInstructor(@RequestBody @Valid Instructor instructor) {
        return service.createInstructor(instructor);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInstructor(@PathVariable int id, @RequestBody @Valid Instructor instructor) {
        instructor.setId(id);
        service.updateInstructor(instructor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInstructor(@PathVariable int id) {
        service.deleteInstructor(id);
    }
}
