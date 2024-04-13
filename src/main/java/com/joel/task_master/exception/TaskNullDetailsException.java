package com.joel.task_master.exception;

public class TaskNullDetailsException extends RuntimeException{
    public TaskNullDetailsException(String message) {
        super(message);
    }
}
