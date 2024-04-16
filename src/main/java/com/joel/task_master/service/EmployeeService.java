package com.joel.task_master.service;

import com.joel.task_master.dto.EmployeeDTO;
import com.joel.task_master.dto.TaskDTO;
import com.joel.task_master.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployeeWithTask(Employee employee);

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO getEmployeeByTaskId(Long taskId);
    EmployeeDTO getEmployeeById(Long empId);
    List<EmployeeDTO> getAllEmployee(Integer pageNumber, Integer pageSize);
    EmployeeDTO updateEmployeeById(Long empId, EmployeeDTO employeeDTO);
    void deleteEmployeeById(Long empId);

}
