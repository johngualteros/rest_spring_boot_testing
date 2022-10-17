package com.john.rest_spring_boot_testing.services.task;

import com.john.rest_spring_boot_testing.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
