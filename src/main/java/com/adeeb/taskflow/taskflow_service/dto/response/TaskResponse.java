package com.adeeb.taskflow.taskflow_service.dto.response;

import com.adeeb.taskflow.taskflow_service.enums.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponse(Long id, String title, String description, LocalDate dueDate, TaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
