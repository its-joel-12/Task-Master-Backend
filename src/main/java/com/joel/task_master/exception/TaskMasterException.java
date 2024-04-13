package com.joel.task_master.exception;

import org.springframework.http.HttpStatus;

public class TaskMasterException {
    // FIELDS ------------------------------------------------------------------------------------------------------
    private final Integer httpCode;
    private final HttpStatus httpStatus;
    private final String message;
    private final String description;
    // FIELDS ------------------------------------------------------------------------------------------------------

    // CONSTRUCTORS ------------------------------------------------------------------------------------------------
    public TaskMasterException(Integer httpCode, HttpStatus httpStatus, String message, String description) {
        this.httpCode = httpCode;
        this.httpStatus = httpStatus;
        this.message = message;
        this.description = description;
    }
    // CONSTRUCTORS ------------------------------------------------------------------------------------------------

    // GETTERS/SETTERS ---------------------------------------------------------------------------------------------
    public Integer getHttpCode() {
        return httpCode;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public String getMessage() {
        return message;
    }
    public String getDescription() {
        return description;
    }
    // GETTERS/SETTERS ---------------------------------------------------------------------------------------------
}
