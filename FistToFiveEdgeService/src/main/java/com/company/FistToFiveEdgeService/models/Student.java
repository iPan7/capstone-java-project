package com.company.FistToFiveEdgeService.models;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Student {

    private Integer id;
    private Integer instructorId;
    private String firstName;
    private String lastName;
    private String studentBio;

    public Student(Integer id, Integer instructorId, String firstName, String lastName, String studentBio) {
        this.id = id;
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentBio = studentBio;
    }

    public Student() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentBio() {
        return studentBio;
    }

    public void setStudentBio(String studentBio) {
        this.studentBio = studentBio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId()) && Objects.equals(getInstructorId(), student.getInstructorId()) && Objects.equals(getFirstName(), student.getFirstName()) && Objects.equals(getLastName(), student.getLastName()) && Objects.equals(getStudentBio(), student.getStudentBio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInstructorId(), getFirstName(), getLastName(), getStudentBio());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", instructorId=" + instructorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentBio='" + studentBio + '\'' +
                '}';
    }
}
