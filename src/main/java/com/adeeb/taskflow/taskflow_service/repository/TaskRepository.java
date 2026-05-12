package com.adeeb.taskflow.taskflow_service.repository;

import com.adeeb.taskflow.taskflow_service.dto.request.CreateTaskRequest;
import com.adeeb.taskflow.taskflow_service.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long id);
    Task save(Task task);
}
