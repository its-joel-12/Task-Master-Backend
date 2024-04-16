package com.joel.task_master.service;

import com.joel.task_master.dto.EmployeeDTO;
import com.joel.task_master.dto.TaskDTO;
import com.joel.task_master.model.Employee;

import java.util.List;

public interface TaskService {

    TaskDTO saveTask(TaskDTO taskDTO, Long empId);
    List<TaskDTO> getTaskByEmployeeId(Long empId);
    TaskDTO getTaskById(Long taskId);
    List<TaskDTO> getAllTask(Integer pageNumber, Integer pageSize);
    TaskDTO updateTaskById(Long taskId, TaskDTO taskDTO);
    void deleteTaskById(Long taskId);

}
