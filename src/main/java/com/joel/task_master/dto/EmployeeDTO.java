package com.joel.task_master.dto;

public class EmployeeDTO {
    // FIELDS ------------------------------------------------------------------------------------------------------
    private Long empId;
    private String empName;
    private String empEmail;
    private String empDesignation;
    // FIELDS ------------------------------------------------------------------------------------------------------

    // CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public EmployeeDTO() {
    }
    public EmployeeDTO(Long empId, String empName, String empEmail, String empDesignation) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.empDesignation = empDesignation;
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
    // GETTERS/SETTERS ---------------------------------------------------------------------------------------------
}
