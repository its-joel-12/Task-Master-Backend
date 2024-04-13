package com.joel.task_master.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Task {
    // FIELDS ------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String taskTitle;
    private String taskDescription;
    private String taskStatus;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "fk_employee")
    @JsonIgnore
    private Employee employee;
    // FIELDS ------------------------------------------------------------------------------------------------------

    // CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public Task() {
    }
    public Task(Long taskId, String taskTitle, String taskDescription, String taskStatus, Date dueDate, Employee employee) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.dueDate = dueDate;
        this.employee = employee;
    }
    // CONSTRUCTORS ------------------------------------------------------------------------------------------------


    // GETTERS/SETTERS ---------------------------------------------------------------------------------------------
    public Long getTaskId() {
        return taskId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    public String getTaskTitle() {
        return taskTitle;
    }
    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public String getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    // GETTERS/SETTERS ---------------------------------------------------------------------------------------------

}
