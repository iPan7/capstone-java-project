package com.company.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "You must supply a first name")
    private String firstName;
    @NotEmpty(message = "You must supply a last name")
    private String lastName;
    @NotEmpty(message = "You must supply a name for the class")
    private String className;
    @OneToMany(mappedBy = "instructorId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Student> students;

    public Instructor(Integer id, String firstName, String lastName, String className, List<Student> students) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.className = className;
        this.students = students;
    }

    public Instructor(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instructor)) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getClassName(), that.getClassName()) && Objects.equals(getStudents(), that.getStudents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getClassName(), getStudents());
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", className='" + className + '\'' +
                ", students=" + students +
                '}';
    }
}
