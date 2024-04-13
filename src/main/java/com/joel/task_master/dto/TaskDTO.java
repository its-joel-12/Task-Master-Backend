package com.joel.task_master.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TaskDTO {
    // FIELDS ------------------------------------------------------------------------------------------------------
    private Long taskId;
    private String taskTitle;
    private String taskDescription;
    private String taskStatus;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dueDate;
    private Long employeeId;
    // FIELDS ------------------------------------------------------------------------------------------------------

    // CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public TaskDTO() {
    }
    public TaskDTO(Long taskId, String taskTitle, String taskDescription, String taskStatus, Date dueDate, Long employeeId) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.dueDate = dueDate;
        this.employeeId = employeeId;
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
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    // GETTERS/SETTERS ---------------------------------------------------------------------------------------------
}
