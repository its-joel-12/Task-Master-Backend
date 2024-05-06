package com.joel.task_master.service;

import com.joel.task_master.dto.EmployeeDTO;
import com.joel.task_master.exception.EmployeeNotFoundException;
import com.joel.task_master.exception.EmployeeNullDetailsException;
import com.joel.task_master.exception.TaskNotFoundException;
import com.joel.task_master.model.Employee;
import com.joel.task_master.model.Task;
import com.joel.task_master.repository.EmployeeRepository;
import com.joel.task_master.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TaskRepository taskRepository;

    // SAVE EMPLOYEE WITH TASKS ---------------------------------------------------------------------
    @Override
    @Transactional
    public Employee saveEmployeeWithTask(Employee employee) {
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee Object is Empty!!");
        } else {
            if (employee.getTasks() == null) {
                throw new TaskNotFoundException("Task object is Empty!!");
            } else {
                employee.getTasks().forEach(task -> task.setEmployee(employee));
                return employeeRepository.save(employee);
            }
        }
    }

    // SAVE EMPLOYEE --------------------------------------------------------------------------------
    @Override
    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO.getEmpName() == null ||
                employeeDTO.getEmpEmail() == null ||
                employeeDTO.getEmpDesignation() == null ||
                employeeDTO.getEmpName().isBlank() ||
                employeeDTO.getEmpEmail().isBlank() ||
                employeeDTO.getEmpDesignation().isBlank()
        ) {
            throw new EmployeeNullDetailsException("Employee details can't be empty or null!");
        } else {
            Employee employee = new Employee();
            employee.setEmpName(employeeDTO.getEmpName().strip());
            employee.setEmpEmail(employeeDTO.getEmpEmail().strip());
            employee.setEmpDesignation(employeeDTO.getEmpDesignation().strip());
            employee = employeeRepository.save(employee);

            employeeDTO.setEmpId(employee.getEmpId());
            return employeeDTO;
        }
    }

    // GET EMPLOYEE BY TASK ID ----------------------------------------------------------------------
    @Override
    @Transactional
    public EmployeeDTO getEmployeeByTaskId(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            Task task = taskRepository.findById(taskId).orElse(null);
            Employee employee = task.getEmployee();

            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmpId(employee.getEmpId());
            employeeDTO.setEmpName(employee.getEmpName());
            employeeDTO.setEmpEmail(employee.getEmpEmail());
            employeeDTO.setEmpDesignation(employee.getEmpDesignation());

            return employeeDTO;
        } else {
            throw new TaskNotFoundException("Task NOT FOUND with the given ID: " + taskId);
        }
    }

    // GET EMPLOYEE BY ID ---------------------------------------------------------------------------
    @Override
    @Transactional
    public EmployeeDTO getEmployeeById(Long empId) {
        if (employeeRepository.existsById(empId)) {
            Employee employee = employeeRepository.findById(empId).orElse(null);

            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmpId(employee.getEmpId());
            employeeDTO.setEmpName(employee.getEmpName());
            employeeDTO.setEmpEmail(employee.getEmpEmail());
            employeeDTO.setEmpDesignation(employee.getEmpDesignation());
            return employeeDTO;
        }
        throw new EmployeeNotFoundException("Employee NOT FOUND with the given ID: " + empId);
    }

    // GET ALL EMPLOYEES ----------------------------------------------------------------------------
    @Override
    @Transactional
    public List<EmployeeDTO> getAllEmployee(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("empId"));

        Page<Employee> allPageEmployees = employeeRepository.findAll(pageable);
        List<Employee> allEmployee = allPageEmployees.getContent();

//        System.out.println("total pages: " + allPageEmployees.getTotalPages());

        if (!allEmployee.isEmpty()) {
            List<EmployeeDTO> employeeDTOList = new ArrayList<>();

            allEmployee.forEach(employee -> employeeDTOList.add(
                    new EmployeeDTO(
                            employee.getEmpId(),
                            employee.getEmpName(),
                            employee.getEmpEmail(),
                            employee.getEmpDesignation()
                    )));
            return employeeDTOList;
        } else {
            throw new EmployeeNotFoundException("There are no Employees in the database :( ");
        }
    }

    // UPDATE EMPLOYEE BY ID ------------------------------------------------------------------------
    @Override
    @Transactional
    public EmployeeDTO updateEmployeeById(Long empId, EmployeeDTO employeeDTO) {
        if (employeeRepository.existsById(empId)) {
            if (employeeDTO == null) {
                throw new EmployeeNotFoundException("Employee Object is Empty!!");
            } else {
                if (employeeDTO.getEmpName() == null ||
                        employeeDTO.getEmpEmail() == null ||
                        employeeDTO.getEmpDesignation() == null ||
                        employeeDTO.getEmpName().isBlank() ||
                        employeeDTO.getEmpEmail().isBlank() ||
                        employeeDTO.getEmpDesignation().isBlank()
                ) {
                    throw new EmployeeNullDetailsException("Employee details can't be empty or null!");
                } else {
                    Employee updatedEmployee = new Employee();
                    updatedEmployee.setEmpId(empId);
                    updatedEmployee.setEmpName(employeeDTO.getEmpName().strip());
                    updatedEmployee.setEmpEmail(employeeDTO.getEmpEmail().strip());
                    updatedEmployee.setEmpDesignation(employeeDTO.getEmpDesignation().strip());
                    employeeRepository.save(updatedEmployee);

                    employeeDTO.setEmpId(empId);
                    return employeeDTO;
                }
            }
        } else {
            throw new EmployeeNotFoundException("Employee NOT FOUND with the given ID: " + empId);
        }
    }

    // DELETE EMPLOYEE BY ID ------------------------------------------------------------------------
    @Override
    @Transactional
    public void deleteEmployeeById(Long empId) {
        if (employeeRepository.existsById(empId)) {
            employeeRepository.deleteById(empId);
        } else {
            throw new EmployeeNotFoundException("Employee doesn't exists in the database with emp ID: " + empId);
        }
    }


}
