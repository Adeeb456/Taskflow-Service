package com.adeeb.taskflow.taskflow_service.controller;

import com.adeeb.taskflow.taskflow_service.dto.request.CreateTaskRequest;
import com.adeeb.taskflow.taskflow_service.dto.request.UpdateTaskRequest;
import com.adeeb.taskflow.taskflow_service.dto.response.TaskResponse;
import com.adeeb.taskflow.taskflow_service.entity.Task;
import com.adeeb.taskflow.taskflow_service.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(){

        List<TaskResponse> taskResponseList = taskService.getAllTasks();

        return ResponseEntity.ok(taskResponseList);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody CreateTaskRequest taskRequest){

        // Request validation is handled automatically
        // using Jakarta Bean Validation annotations.
        TaskResponse createdTask = taskService.createTask(taskRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTask);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(
            @PathVariable("taskId") Long taskId) throws Exception {

        Task task = taskService.getTaskById(taskId);

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {

        taskService.deleteTask(taskId);

        // Returning HTTP 204 to indicate successful deletion
        // without sending response body content.
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable("taskId") Long taskId,
            @Valid @RequestBody UpdateTaskRequest updateTaskRequest) throws Exception {

        TaskResponse updatedTask =
                taskService.updateTask(taskId, updateTaskRequest);

        return ResponseEntity.ok(updatedTask);
    }

    @PatchMapping("/{taskId}/mark-completed")
    public ResponseEntity<TaskResponse> markTaskAsCompleted(
            @PathVariable Long taskId) throws Exception {

        // PATCH endpoint used for partial resource state transition.
        TaskResponse completedTask = taskService.completeTask(taskId);

        return ResponseEntity.ok(completedTask);
    }

    @PatchMapping("/{taskId}/start")
    public ResponseEntity<TaskResponse> markTaskAsInProgress(
            @PathVariable Long taskId) throws Exception {

        TaskResponse completedTask = taskService.startTask(taskId);

        return ResponseEntity.ok(completedTask);
    }
}