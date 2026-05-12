package com.adeeb.taskflow.taskflow_service.exception.customExceptions;

public class InvalidTaskStateException extends Exception {
    public InvalidTaskStateException(String message){
        super(message);
    }
}
