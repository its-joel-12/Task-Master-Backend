package com.joel.task_master.controller;

import com.joel.task_master.dto.TaskDTO;
import com.joel.task_master.exception.TaskMasterException;
import com.joel.task_master.service.EmployeeService;
import com.joel.task_master.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-master/api")
@Tag(name = "Task Controller")
public class TaskController {

    @Autowired
    private TaskService taskService;

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
        return new ResponseEntity<>(taskService.saveTask(taskDTO, empId), HttpStatus.CREATED);
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
        return new ResponseEntity<>(taskService.updateTaskById(taskId, taskDTO), HttpStatus.OK);
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
        return new ResponseEntity<>(taskService.getTaskByEmployeeId(empId), HttpStatus.OK);
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
        return new ResponseEntity<>(taskService.getTaskById(taskId), HttpStatus.OK);
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
    @CrossOrigin
    @GetMapping("/task")
    public ResponseEntity<List<TaskDTO>> getAllTask(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber, @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize) {
        return new ResponseEntity<>(taskService.getAllTask(pageNumber, pageSize), HttpStatus.OK);
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
        taskService.deleteTaskById(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
