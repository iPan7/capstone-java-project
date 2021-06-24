package com.company.service;

import com.company.model.Student;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentApiServiceLayer {

    StudentRepository studentRepo;

    @Autowired
    public StudentApiServiceLayer(StudentRepository repo) {
        this.studentRepo = repo;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepo.getById(id);
    }

    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public void updateStudent(Student student) {
        studentRepo.save(student);
    }

    public void deleteStudent(int id) {
        studentRepo.deleteById(id);
    }
}
