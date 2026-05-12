package com.adeeb.taskflow.taskflow_service.service;

import com.adeeb.taskflow.taskflow_service.dto.request.CreateTaskRequest;
import com.adeeb.taskflow.taskflow_service.dto.request.UpdateTaskRequest;
import com.adeeb.taskflow.taskflow_service.dto.response.TaskResponse;
import com.adeeb.taskflow.taskflow_service.entity.Task;
import com.adeeb.taskflow.taskflow_service.enums.TaskStatus;
import com.adeeb.taskflow.taskflow_service.exception.customExceptions.InvalidTaskStateException;
import com.adeeb.taskflow.taskflow_service.exception.customExceptions.TaskNotFoundException;
import com.adeeb.taskflow.taskflow_service.repository.TaskRepository;
import com.adeeb.taskflow.taskflow_service.util.mapper.TaskMapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<TaskResponse> getAllTasks(){

        List<Task> taskList = taskRepository.findAll();

        // Converting entity objects into response DTOs
        // to avoid exposing internal entity structure.
        return taskList.stream()
                .map(TaskMapperUtil::mapTasktoTaskResponse)
                .toList();
    }

    public TaskResponse createTask(CreateTaskRequest taskRequest){

        Task task = TaskMapperUtil.mapCreateTaskRequestToTask(taskRequest);

        Task createdTask = taskRepository.save(task);

        return TaskMapperUtil.mapTasktoTaskResponse(createdTask);
    }

    public Task getTaskById(Long taskId) throws Exception {

        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if(taskOptional.isPresent()){
            return taskOptional.get();
        }

        throw new TaskNotFoundException("No task is found with task ID : " + taskId);
    }

    public void deleteTask(Long id){

        taskRepository.deleteById(id);
    }

    public TaskResponse updateTask(Long id, UpdateTaskRequest updateTaskRequest) throws Exception {

        Task task = getTaskById(id);

        task.setTitle(updateTaskRequest.title());
        task.setDescription(updateTaskRequest.description());
        task.setDueDate(updateTaskRequest.dueDate());

        Task updatedTask = taskRepository.save(task);

        return new TaskResponse(
                updatedTask.getId(),
                updatedTask.getTitle(),
                updatedTask.getDescription(),
                updatedTask.getDueDate(),
                updatedTask.getStatus(),
                updatedTask.getCreatedAt(),
                updatedTask.getUpdatedAt()
        );
    }

    public TaskResponse completeTask(Long taskId) throws Exception {

        Task task = getTaskById(taskId);

        // Preventing redundant state transition
        // if the task is already completed.
        if(task.getStatus().name().equals("COMPLETED")){
            throw new InvalidTaskStateException("Task is already marked as completed!");
        }

        task.setStatus(TaskStatus.COMPLETED);

        Task updatedTask = taskRepository.save(task);

        return TaskMapperUtil.mapTasktoTaskResponse(updatedTask);
    }

    public TaskResponse startTask(Long taskId) throws Exception {

        Task task = getTaskById(taskId);

        // Updating task workflow state from PENDING to IN_PROGRESS.
        task.setStatus(TaskStatus.IN_PROGRESS);

        Task updatedTask = taskRepository.save(task);

        return TaskMapperUtil.mapTasktoTaskResponse(updatedTask);
    }
}