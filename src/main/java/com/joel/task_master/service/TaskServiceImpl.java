package com.joel.task_master.service;

import com.joel.task_master.dto.EmployeeDTO;
import com.joel.task_master.dto.TaskDTO;
import com.joel.task_master.exception.EmployeeNotFoundException;
import com.joel.task_master.exception.EmployeeNullDetailsException;
import com.joel.task_master.exception.TaskNotFoundException;
import com.joel.task_master.exception.TaskNullDetailsException;
import com.joel.task_master.model.Employee;
import com.joel.task_master.model.Task;
import com.joel.task_master.repository.EmployeeRepository;
import com.joel.task_master.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service

public class TaskServiceImpl implements TaskService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TaskRepository taskRepository;


    // SAVE TASK WITH EMP ID ------------------------------------------------------------------------
    @Override
    @Transactional
    public TaskDTO saveTask(TaskDTO taskDTO, Long empId) {
        if (taskDTO.getTaskTitle() == null ||
                taskDTO.getTaskDescription() == null ||
                taskDTO.getTaskStatus() == null ||
                taskDTO.getDueDate() == null ||
                taskDTO.getTaskTitle().isBlank() ||
                taskDTO.getTaskDescription().isBlank() ||
                taskDTO.getTaskStatus().isBlank()
        ) {
            throw new TaskNullDetailsException("Task details can't be empty or null!");
        } else {
            if (employeeRepository.existsById(empId)) {
                Employee employee = employeeRepository.findById(empId).orElse(null);
                Task task = new Task();
                task.setTaskTitle(taskDTO.getTaskTitle().strip());
                task.setTaskDescription(taskDTO.getTaskDescription().strip());
                task.setTaskStatus(taskDTO.getTaskStatus().strip());
                task.setDueDate(taskDTO.getDueDate());
                task.setEmployee(employee);

                task = taskRepository.save(task);

                taskDTO.setTaskId(task.getTaskId());
                taskDTO.setEmployeeId(employee.getEmpId());

                return taskDTO;
            } else {
                throw new EmployeeNotFoundException("Employee NOT FOUND with the given ID: " + empId);
            }
        }

    }

    // GET TASK BY EMP-ID ---------------------------------------------------------------------------
    @Override
    @Transactional
    public List<TaskDTO> getTaskByEmployeeId(Long empId) {
        if (employeeRepository.existsById(empId)) {
            Employee employee = employeeRepository.findById(empId).orElse(null);

            List<Task> tasks = employee.getTasks();
            if (!tasks.isEmpty()) {

                List<TaskDTO> taskList = new ArrayList<>();
                tasks.forEach(task ->
                        taskList.add(
                                new TaskDTO(
                                        task.getTaskId(),
                                        task.getTaskTitle(),
                                        task.getTaskDescription(),
                                        task.getTaskStatus(),
                                        task.getDueDate(),
                                        task.getEmployee().getEmpId()
                                ))
                );
                return taskList;
            } else {
                throw new TaskNotFoundException("No task is assigned to this user!!");
            }
        } else {
            throw new EmployeeNotFoundException("Employee NOT FOUND with the given ID: " + empId);
        }
    }

    // GET TASK BY ID -------------------------------------------------------------------------------
    @Override
    @Transactional
    public TaskDTO getTaskById(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            Task task = taskRepository.findById(taskId).orElse(null);

            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setTaskId(task.getTaskId());
            taskDTO.setTaskTitle(task.getTaskTitle());
            taskDTO.setTaskDescription(task.getTaskDescription());
            taskDTO.setTaskStatus(task.getTaskStatus());
            taskDTO.setDueDate(task.getDueDate());
            taskDTO.setEmployeeId(task.getEmployee().getEmpId());

            return taskDTO;
        } else {
            throw new TaskNotFoundException("Task NOT FOUND with the given ID: " + taskId);
        }
    }

    // GET ALL TASKS --------------------------------------------------------------------------------
    @Override
    @Transactional
    public List<TaskDTO> getAllTask(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Task> allPageTasks = taskRepository.findAll(pageable);
        List<Task> allTask = allPageTasks.getContent();

//        System.out.println("total no of elements: " + allPageTasks.getTotalElements());

        if (!allTask.isEmpty()) {
            List<TaskDTO> taskDTOList = new ArrayList<>();

            allTask.forEach(task ->
                    taskDTOList.add(new TaskDTO(
                            task.getTaskId(),
                            task.getTaskTitle(),
                            task.getTaskDescription(),
                            task.getTaskStatus(),
                            task.getDueDate(),
                            task.getEmployee().getEmpId()
                    )));
            return taskDTOList;
        } else {
            throw new TaskNotFoundException("There are no tasks in the database :( ");
        }
    }

    // UPDATE TASK BY ID ----------------------------------------------------------------------------
    @Override
    @Transactional
    public TaskDTO updateTaskById(Long taskId, TaskDTO taskDTO) {
        if (taskRepository.existsById(taskId)) {
            if (taskDTO == null) {
                throw new TaskNotFoundException("Task Object is Empty!!");
            } else {
                if (taskDTO.getTaskTitle() == null ||
                        taskDTO.getTaskDescription() == null ||
                        taskDTO.getTaskStatus() == null ||
                        taskDTO.getDueDate() == null ||
                        taskDTO.getTaskTitle().isBlank() ||
                        taskDTO.getTaskDescription().isBlank() ||
                        taskDTO.getTaskStatus().isBlank()
                ) {
                    throw new TaskNullDetailsException("Task details can't be empty or null!");
                } else {
                    Task updatedTask = taskRepository.findById(taskId).orElse(null);

                    updatedTask.setTaskId(taskId);
                    updatedTask.setTaskTitle(taskDTO.getTaskTitle().strip());
                    updatedTask.setTaskDescription(taskDTO.getTaskDescription().strip());
                    updatedTask.setTaskStatus(taskDTO.getTaskStatus().strip());
                    updatedTask.setDueDate(taskDTO.getDueDate());
                    taskRepository.save(updatedTask);

                    taskDTO.setEmployeeId(updatedTask.getEmployee().getEmpId());
                    taskDTO.setTaskId(taskId);

                    return taskDTO;
                }
            }
        } else {
            throw new TaskNotFoundException("Task NOT FOUND with the given ID: " + taskId);
        }
    }

    // DELETE TASK BY ID ----------------------------------------------------------------------------
    @Override
    @Transactional
    public void deleteTaskById(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        } else {
            throw new TaskNotFoundException("Task doesn't exists in the database with task ID: " + taskId);
        }
    }

}
