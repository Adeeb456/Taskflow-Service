package com.adeeb.taskflow.taskflow_service.service;

import com.adeeb.taskflow.taskflow_service.entity.Task;
import com.adeeb.taskflow.taskflow_service.enums.TaskStatus;
import com.adeeb.taskflow.taskflow_service.exception.customExceptions.InvalidTaskStateException;
import com.adeeb.taskflow.taskflow_service.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldMarkTaskAsCompleted() throws Exception {

        // preparing mocks

        Long taskId = 1L;

        Task task = new Task();

        task.setId(taskId);
        task.setTitle("API Project");
        task.setDescription("API project has to be implemented in java and spring boot");
        task.setDueDate(LocalDate.now().plusDays(3));
        task.setStatus(TaskStatus.IN_PROGRESS);

        when(taskRepository.findById(taskId))
                .thenReturn(Optional.of(task));

        when(taskRepository.save(task))
                .thenReturn(task);

        // calling service method

        taskService.completeTask(taskId);

        // assertion
        assertEquals(TaskStatus.COMPLETED, task.getStatus());
    }

    @Test
    public void shouldThrowExceptionWhenTaskIsAlreadyCompleted() throws Exception {
        // preparing mocks

        Long taskId = 1L;

        Task task = new Task();

        task.setId(taskId);
        task.setTitle("API Project");
        task.setDescription("API project has to be implemented in java and spring boot");
        task.setDueDate(LocalDate.now().plusDays(3));
        task.setStatus(TaskStatus.COMPLETED);

        when(taskRepository.findById(taskId))
                .thenReturn(Optional.of(task));

        // assertion
        assertThrows(InvalidTaskStateException.class, () -> taskService.completeTask(taskId));
    }
}