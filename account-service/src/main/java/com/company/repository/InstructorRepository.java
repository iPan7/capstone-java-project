package com.company.repository;

import com.company.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Instructor getById(int id);
}
