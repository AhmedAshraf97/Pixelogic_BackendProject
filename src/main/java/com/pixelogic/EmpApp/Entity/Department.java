package com.pixelogic.EmpApp.Entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="Departments")
public class Department {

    @Id
    @GeneratedValue
    private Long Department_id;



    @Column(name = "Department_name",unique = true)
    @NotNull
    private String Department_name;


    private Long Leader_id;


    public String getDepartment_name() {
        return Department_name;
    }

    public Long getDepartment_id() {
        return Department_id;
    }


    public void setDepartment_id(Long department_id) {
        Department_id = department_id;
    }

    public void setDepartment_name(String department_name) {
        Department_name = department_name;
    }

    public Long getLeader_id() {
        return Leader_id;
    }

    public void setLeader_id(Long leader) {
        Leader_id = leader;
    }




}
