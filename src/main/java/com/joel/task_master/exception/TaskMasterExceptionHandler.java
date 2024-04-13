package com.joel.task_master.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class TaskMasterExceptionHandler {

    @ExceptionHandler(value = {
            EmployeeNotFoundException.class,
            NumberFormatException.class,
            Exception.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<Object> handleExceptions(Exception ex) {
        // EMPLOYEE NOT FOUND EXCEPTION
        if (ex instanceof EmployeeNotFoundException) {
            TaskMasterException error = new TaskMasterException(
                    404,
                    HttpStatus.NOT_FOUND,
                    ex.getMessage(),
                    "Employee(s) not found, hence the operation was unsuccessful!!"
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        // HttpMessageNotReadableException
        else if (ex instanceof HttpMessageNotReadableException) {
            TaskMasterException error = new TaskMasterException(
                    400,
                    HttpStatus.BAD_REQUEST,
                    "Please make sure you send data in proper format, also don't send an empty request body!!",
                    ex.getMessage()
                    );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        // TASK NOT FOUND EXCEPTION
        else if (ex instanceof TaskNotFoundException) {
            TaskMasterException error = new TaskMasterException(
                    404,
                    HttpStatus.NOT_FOUND,
                    ex.getMessage(),
                    "Task(s) not found, hence the operation was unsuccessful!!"
                    );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        // EMPLOYEE NULL DETAILS EXCEPTION
        else if (ex instanceof EmployeeNullDetailsException) {
            TaskMasterException error = new TaskMasterException(
                    400,
                    HttpStatus.BAD_REQUEST,
                    ex.getMessage(),
                    "Name, Email and Designation can't be null or empty and neither blank spaces!!"
                    );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        // TASK NULL DETAILS EXCEPTION
        else if (ex instanceof TaskNullDetailsException) {
            TaskMasterException error = new TaskMasterException(
                    400,
                    HttpStatus.BAD_REQUEST,
                    ex.getMessage(),
                    "Title, Description, Status and Due date can't be null or empty and neither blank spaces!!"
                    );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        // NoResourceFoundException
        else if (ex instanceof NoResourceFoundException) {
            TaskMasterException error = new TaskMasterException(
                    400,
                    HttpStatus.BAD_REQUEST,
                    "Check proper format of the api and its Http method!!",
                    ex.getMessage()
                    );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        // MethodArgumentTypeMismatchException
        else if (ex instanceof MethodArgumentTypeMismatchException) {
            TaskMasterException error = new TaskMasterException(
                    400,
                    HttpStatus.BAD_REQUEST,
                    "Please provide proper Integer ID input in the api url argument!!",
                    ex.getMessage()
                    );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        // UN IDENTIFIED EXCEPTION
        else {
            TaskMasterException error = new TaskMasterException(
                    500,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Un identified error | Please contact the Backend Developer!!... :D",
                    ex.getMessage()
                    );
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
