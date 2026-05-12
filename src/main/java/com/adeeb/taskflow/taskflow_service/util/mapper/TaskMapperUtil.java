package com.adeeb.taskflow.taskflow_service.util.mapper;

import com.adeeb.taskflow.taskflow_service.dto.request.CreateTaskRequest;
import com.adeeb.taskflow.taskflow_service.dto.response.TaskResponse;
import com.adeeb.taskflow.taskflow_service.entity.Task;

public class TaskMapperUtil {

    // Converts Task entity into API response DTO.
    // This prevents exposing internal entity structure directly to clients.
    public static TaskResponse mapTasktoTaskResponse(Task task){

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    // Converts incoming request DTO into Task entity
    // before persisting data into the database.
    public static Task mapCreateTaskRequestToTask(CreateTaskRequest taskRequest){

        return new Task(
                taskRequest.title(),
                taskRequest.description(),
                taskRequest.dueDate()
        );
    }
}