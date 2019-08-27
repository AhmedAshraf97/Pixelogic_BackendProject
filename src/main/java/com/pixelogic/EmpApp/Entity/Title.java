package com.pixelogic.EmpApp.Entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="Titles")
public class Title {

    @Id
    @GeneratedValue
    private Long Title_id;


    @Column(name = "Title_name")
    @NotNull
    private String Title_name;


    @ManyToOne
    @NotNull
    Department Department;

    public Long getTitle_id() {
        return Title_id;
    }

    public void setTitle_id(Long title_id) {
        Title_id = title_id;
    }

    public String getTitle_name() {
        return Title_name;
    }

    public void setTitle_name(String title_name) {
        Title_name = title_name;
    }

    public Department getDepartment() {
        return Department;
    }

    public void setDepartment(Department department) {
        this.Department = department;
    }
}