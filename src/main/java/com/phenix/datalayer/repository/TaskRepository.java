package com.phenix.datalayer.repository;

/**
 * Created by Dmitry Azarov on 7/13/2019.
 */

import com.phenix.datalayer.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}