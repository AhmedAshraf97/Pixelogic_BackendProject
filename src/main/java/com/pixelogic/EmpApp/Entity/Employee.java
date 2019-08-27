package com.pixelogic.EmpApp.Entity;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name ="Employee")
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "Username")
    @NotNull
    private String Username;


    @Column(name = "Birthdate")
    @NotNull
    private LocalDate Birthdate;


    @Column(name = "Age")
    @NotNull
    private int Age;

    @Column(name = "Email", unique = true)
    @NotNull
    private String Email;

    @ManyToOne
    @NotNull
    private Title Title;

//    @NotNull
//    @ManyToOne
//    private Department Department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public LocalDate getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(int day, int month, int year) {
        Birthdate = LocalDate.of(year, month, day);
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setBirthdate(LocalDate birthdate) {
        Birthdate = birthdate;
    }

    public com.pixelogic.EmpApp.Entity.Title getTitle() {
        return Title;
    }

    public void setTitle(com.pixelogic.EmpApp.Entity.Title title) {
        Title = title;
    }

//    public Department getDepartment() {
//        return Department;
//    }
//
//    public void setDepartment(Department department) {
//        Department = department;
//    }
}
