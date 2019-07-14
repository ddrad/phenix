package com.phenix.service.product;

import com.phenix.datalayer.repository.TaskRepository;
import com.phenix.datalayer.entity.Task;
import com.phenix.model.TaskInfo;
import com.phenix.model.builder.TaskInfoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Dmitry Azarov on 7/13/2019.
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    public Function<Task, TaskInfo> taskInfoConverter = e -> new TaskInfoBuilder()
            .withId(e.getId())
            .withName(e.getName())
            .withStatus(e.getStatus())
            .withType(e.getType())
            .withAuthor(e.getAuthor())
            .withAssignedTo(e.getAssignedTo())
            .withAppointed(e.getAppointed())
            .withCreated(e.getCreated())
            .withLastUpdated(e.getLastUpdated())
            .build();

    @Transactional
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public Task getTaskById(long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity was not found"));
    }

    @Transactional
    public List<TaskInfo> getAllTask() {
        return taskRepository.findAll().stream().map(taskInfoConverter).collect(Collectors.toList());
    }

    @Transactional
    public Task openTaskDetails(long id) {
        Task task = getTaskById(id);
        if (task.isExecuteNow()) {
            throw new RuntimeException("Card is opening by another operator");
        }
        task.setExecuteNow(true);
        save(task);
        return task;
    }

    @Transactional
    public void closeTask(Task task) {
        task.setExecuteNow(false);
        save(task);
    }

    public TaskInfo toTaskInfo(Task entity) {
        if(entity == null)
            throw new RuntimeException("Convert Fault: Entity is null");
        return taskInfoConverter.apply(entity);
    }
}
