package com.joel.task_master.controller;

import com.joel.task_master.dto.EmployeeDTO;
import com.joel.task_master.dto.EmployeeTaskDTO;
import com.joel.task_master.dto.TaskDTO;
import com.joel.task_master.exception.EmployeeNotFoundException;
import com.joel.task_master.exception.TaskMasterException;
import com.joel.task_master.model.Employee;
import com.joel.task_master.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-master/api")
@Tag(name = "TaskMaster API Controller")
public class AppController {

    @Autowired
    private Service service;

    // SAVE EMPLOYEE WITH TASKS ---------------------------------------------------------------------
    @Operation(
            summary = "SAVE EMPLOYEE WITH TASK(S)",
            description = "You can save the task(s) while saving the Employee",
            responses = {
                    @ApiResponse(
                            description = "CREATED",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Employee.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Employee/Task object not found | NOT_FOUND",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                                    /*
                                    examples = @ExampleObject(
                                    name = "errorResponse",
                                    value = "{\"message\": \"Employee/Task object not found\", \"errorCode\": \"404\"}"
                            )*/
                            )
                    ),
                    @ApiResponse(
                            description = "Client Side Error | BAD_REQUEST",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Server Side Error | INTERNAL_SERVER_ERROR",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    )
            }
    )
    @CrossOrigin
    @PostMapping("/employee-task")
    public ResponseEntity<Employee> saveEmployeeWithTask(@RequestBody EmployeeTaskDTO employeeTaskDTO) {
        Employee employee = service.saveEmployeeWithTask(employeeTaskDTO.getEmployee());
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    // SAVE EMPLOYEE --------------------------------------------------------------------------------
    @Operation(
            summary = "SAVE EMPLOYEE",
            description = "You can save the Employee",
            responses = {
                    @ApiResponse(
                            description = "CREATED",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeDTO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Employee/Task object not found | NOT_FOUND",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Client Side Error | BAD_REQUEST",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Server Side Error | INTERNAL_SERVER_ERROR",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    )
            }
    )
    @PostMapping("/employee")
    @CrossOrigin
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(service.saveEmployee(employeeDTO), HttpStatus.CREATED);
    }

    // SAVE TASK WITH EMP ID ------------------------------------------------------------------------
    @Operation(
            summary = "SAVE TASK WITH EMPLOYEE ID",
            description = "You can save Task with Employee ID",
            responses = {
                    @ApiResponse(
                            description = "CREATED",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskDTO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Employee/Task object not found | NOT_FOUND",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Client Side Error | BAD_REQUEST",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Server Side Error | INTERNAL_SERVER_ERROR",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    )
            }
    )
    @PostMapping("/task/{empId}")
    @CrossOrigin
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO taskDTO, @PathVariable("empId") Long empId) {
        return new ResponseEntity<>(service.saveTask(taskDTO, empId), HttpStatus.CREATED);
    }

    // UPDATE EMPLOYEE BY ID ------------------------------------------------------------------------
    @Operation(
            summary = "UPDATE EMPLOYEE BY ID",
            description = "You can updated the Employee by ID",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeDTO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Employee/Task object not found | NOT_FOUND",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Client Side Error | BAD_REQUEST",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Server Side Error | INTERNAL_SERVER_ERROR",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    )
            }
    )
    @PutMapping("/employee/{empId}")
    @CrossOrigin
    public ResponseEntity<EmployeeDTO> updateEmpById(@PathVariable("empId") Long empId, @RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(service.updateEmployeeById(empId, employeeDTO), HttpStatus.OK);
    }

    // UPDATE TASK BY ID ----------------------------------------------------------------------------
    @Operation(
            summary = "UPDATE TASK BY ID",
            description = "You can update task ny ID",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskDTO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Employee/Task object not found | NOT_FOUND",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Client Side Error | BAD_REQUEST",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Server Side Error | INTERNAL_SERVER_ERROR",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    )
            }
    )

    @PutMapping("/task/{taskId}")
    @CrossOrigin
    public ResponseEntity<TaskDTO> updateTaskById(@PathVariable("taskId") Long taskId, @RequestBody TaskDTO taskDTO) {
        return new ResponseEntity<>(service.updateTaskById(taskId, taskDTO), HttpStatus.OK);
    }

    // GET EMPLOYEE BY TASK ID ----------------------------------------------------------------------
    @Operation(
            summary = "GET EMPLOYEE BY TASK ID",
            description = "You can get employee by Task ID",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeDTO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Employee/Task object not found | NOT_FOUND",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Client Side Error | BAD_REQUEST",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Server Side Error | INTERNAL_SERVER_ERROR",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    )
            }
    )

    @GetMapping("/employee-task-id/{taskId}")
    @CrossOrigin
    public ResponseEntity<EmployeeDTO> getEmployeeByTaskId(@PathVariable("taskId") Long taskId) {
        return new ResponseEntity<>(service.getEmployeeByTaskId(taskId), HttpStatus.OK);
    }

    // GET EMPLOYEE BY ID ---------------------------------------------------------------------------
    @Operation(
            summary = "GET EMPLOYEE BY ID",
            description = "You can get employee by ID",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeDTO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Employee/Task object not found | NOT_FOUND",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Client Side Error | BAD_REQUEST",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Server Side Error | INTERNAL_SERVER_ERROR",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    )
            }
    )

    @GetMapping("/employee/{empId}")
    @CrossOrigin
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("empId") Long empId) {
        return new ResponseEntity<>(service.getEmployeeById(empId), HttpStatus.OK);
    }

    // GET ALL EMPLOYEES ----------------------------------------------------------------------------
    @Operation(
            summary = "GET ALL EMPLOYEES",
            description = "You can get all employees",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
/*
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = List.class,
                                            allowableValues = { "List<EmployeeDTO.class>" })
                            )
*/
                    ),
                    @ApiResponse(
                            description = "Employee/Task object not found | NOT_FOUND",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Client Side Error | BAD_REQUEST",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Server Side Error | INTERNAL_SERVER_ERROR",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    )
            }
    )

    @GetMapping("/employee")
    @CrossOrigin
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        return new ResponseEntity<>(service.getAllEmployee(), HttpStatus.OK);
    }

    // GET TASK BY EMP-ID ---------------------------------------------------------------------------
    @Operation(
            summary = "GET TASK BY EMP-ID",
            description = "You can get Task by Employee ID",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
/*
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeDTO.class)
                            )
*/
                    ),
                    @ApiResponse(
                            description = "Employee/Task object not found | NOT_FOUND",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Client Side Error | BAD_REQUEST",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Server Side Error | INTERNAL_SERVER_ERROR",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    )
            }
    )

    @GetMapping("/task-emp-id/{empId}")
    @CrossOrigin
    public ResponseEntity<List<TaskDTO>> getTaskByEmpId(@PathVariable("empId") Long empId) {
        return new ResponseEntity<>(service.getTaskByEmployeeId(empId), HttpStatus.OK);
    }

    // GET TASK BY ID -------------------------------------------------------------------------------
    @Operation(
            summary = "GET TASK BY ID",
            description = "You can get task by ID",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskDTO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Employee/Task object not found | NOT_FOUND",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Client Side Error | BAD_REQUEST",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Server Side Error | INTERNAL_SERVER_ERROR",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    )
            }
    )

    @GetMapping("/task/{taskId}")
    @CrossOrigin
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("taskId") Long taskId) {
        return new ResponseEntity<>(service.getTaskById(taskId), HttpStatus.OK);
    }

    // GET ALL TASKS --------------------------------------------------------------------------------
    @Operation(
            summary = "GET ALL TASKS",
            description = "You can all Tasks",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
/*
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployeeDTO.class)
                            )
*/
                    ),
                    @ApiResponse(
                            description = "Employee/Task object not found | NOT_FOUND",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Client Side Error | BAD_REQUEST",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Server Side Error | INTERNAL_SERVER_ERROR",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    )
            }
    )

    @GetMapping("/task")
    @CrossOrigin
    public ResponseEntity<List<TaskDTO>> getAllTask() {
        return new ResponseEntity<>(service.getAllTask(), HttpStatus.OK);
    }

    // DELETE EMPLOYEE BY ID ------------------------------------------------------------------------
    @Operation(
            summary = "DELETE EMPLOYEE BY ID",
            description = "You can delete employee by ID",
            responses = {
                    @ApiResponse(
                            description = "NO_CONTENT",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Employee/Task object not found | NOT_FOUND",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Client Side Error | BAD_REQUEST",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Server Side Error | INTERNAL_SERVER_ERROR",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    )
            }
    )

    @DeleteMapping("/employee/{empId}")
    @CrossOrigin
    public ResponseEntity<Void> deleteEmpById(@PathVariable("empId") Long empId) {
        service.deleteEmployeeById(empId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE TASK BY ID ----------------------------------------------------------------------------
    @Operation(
            summary = "DELETE TASK BY ID",
            description = "You can delete task by ID",
            responses = {
                    @ApiResponse(
                            description = "NO_CONTENT",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Employee/Task object not found | NOT_FOUND",
                            responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Client Side Error | BAD_REQUEST",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Server Side Error | INTERNAL_SERVER_ERROR",
                            responseCode = "500",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskMasterException.class)
                            )
                    )
            }
    )

    @DeleteMapping("/task/{taskId}")
    @CrossOrigin
    public ResponseEntity<Void> deleteTaskById(@PathVariable("taskId") Long taskId) {
        service.deleteTaskById(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
