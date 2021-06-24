package com.company.FistToFiveEdgeService.util.feign;

import com.company.FistToFiveEdgeService.models.Instructor;
import com.company.FistToFiveEdgeService.models.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

    @GetMapping("/instructor")
    public List<Instructor> getAllInstructors();

    @GetMapping("/instructor/{id}")
    public Instructor getInstructorById(@PathVariable int id);

    @PostMapping("/instructor")
    public Instructor createInstructor(@RequestBody Instructor instructor);

    @PutMapping("/instructor/{id}")
    public void updateInstructor(@PathVariable int id, @RequestBody Instructor instructor);

    @DeleteMapping("/instructor/{id}")
    public void deleteInstructor(@PathVariable int id);

    @GetMapping("/student")
    public List<Student> getAllStudents();

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable int id);

    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student);

    @PutMapping("/student/{id}")
    public void updateStudent(@PathVariable int id, @RequestBody Student student);

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable int id);

}
