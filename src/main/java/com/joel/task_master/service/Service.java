package com.joel.task_master.service;

import com.joel.task_master.dto.EmployeeDTO;
import com.joel.task_master.dto.TaskDTO;
import com.joel.task_master.model.Employee;
import com.joel.task_master.model.Task;

import java.util.List;

public interface Service {
    Employee saveEmployeeWithTask(Employee employee);

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO getEmployeeByTaskId(Long taskId);
    EmployeeDTO getEmployeeById(Long empId);
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO updateEmployeeById(Long empId, EmployeeDTO employeeDTO);
    void deleteEmployeeById(Long empId);


    TaskDTO saveTask(TaskDTO taskDTO, Long empId);
    List<TaskDTO> getTaskByEmployeeId(Long empId);
    TaskDTO getTaskById(Long taskId);
    List<TaskDTO> getAllTask();
    TaskDTO updateTaskById(Long taskId, TaskDTO taskDTO);
    void deleteTaskById(Long taskId);

}
