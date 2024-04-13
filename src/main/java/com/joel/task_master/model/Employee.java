package com.joel.task_master.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee {
    // FIELDS ------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empIdGenerator")
    @SequenceGenerator(name = "empIdGenerator", sequenceName = "joelSequence", allocationSize = 1, initialValue = 101)
    private Long empId;
    private String empName;
    private String empEmail;
    private String empDesignation;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Task> tasks;
    // FIELDS ------------------------------------------------------------------------------------------------------

    // CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public Employee() {
    }
    public Employee(Long empId, String empName, String empEmail, String empDesignation, List<Task> tasks) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.empDesignation = empDesignation;
        this.tasks = tasks;
    }
    // CONSTRUCTORS ------------------------------------------------------------------------------------------------


    // GETTERS/SETTERS ---------------------------------------------------------------------------------------------
    public Long getEmpId() {
        return empId;
    }
    public void setEmpId(Long empId) {
        this.empId = empId;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public String getEmpEmail() {
        return empEmail;
    }
    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }
    public String getEmpDesignation() {
        return empDesignation;
    }
    public void setEmpDesignation(String empDesignation) {
        this.empDesignation = empDesignation;
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    // GETTERS/SETTERS ---------------------------------------------------------------------------------------------

}
