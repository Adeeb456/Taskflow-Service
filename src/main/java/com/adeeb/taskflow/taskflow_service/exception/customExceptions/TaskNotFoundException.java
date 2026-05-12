package com.adeeb.taskflow.taskflow_service.exception.customExceptions;

import com.adeeb.taskflow.taskflow_service.entity.Task;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(String message){
        super(message);
    }
}
