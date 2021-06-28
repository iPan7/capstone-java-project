package com.company.service;

import com.company.model.Instructor;
import com.company.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorApiServiceLayer {

    InstructorRepository instructorRepo;

    @Autowired
    public InstructorApiServiceLayer(InstructorRepository repo) {
        this.instructorRepo = repo;
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepo.findAll();
    }

    public Instructor getInstructorById(int id) {
        return instructorRepo.getById(id);
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepo.save(instructor);
    }

    public void updateInstructor(Instructor instructor) {
        instructorRepo.save(instructor);
    }

    public void deleteInstructor(int id) {
        instructorRepo.deleteById(id);
    }

}
