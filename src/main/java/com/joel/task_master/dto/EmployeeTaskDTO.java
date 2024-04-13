package com.joel.task_master.dto;

import com.joel.task_master.model.Employee;

public class EmployeeTaskDTO {
    // FIELDS ------------------------------------------------------------------------------------------------------
    private Employee employee;
    // FIELDS ------------------------------------------------------------------------------------------------------

    // CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public EmployeeTaskDTO() {
    }
    public EmployeeTaskDTO(Employee employee) {
        this.employee = employee;
    }
    // CONSTRUCTORS ------------------------------------------------------------------------------------------------

    // GETTERS/SETTERS ---------------------------------------------------------------------------------------------
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    // GETTERS/SETTERS ---------------------------------------------------------------------------------------------
}
