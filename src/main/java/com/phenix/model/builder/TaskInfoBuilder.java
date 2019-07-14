package com.phenix.model.builder;

import com.phenix.datalayer.entity.Task;
import com.phenix.model.TaskInfo;
import com.phenix.model.TaskStatus;
import com.phenix.model.TaskType;
import java.time.LocalDateTime;

public class TaskInfoBuilder {

    private long id;
    private String name;
    private TaskType type;
    private TaskStatus status;
    private String author;
    private String assignedTo;
    private String appointed;
    private LocalDateTime created;
    private LocalDateTime lastUpdated;

    public TaskInfoBuilder() {
    }

    public TaskInfoBuilder withId(long id){
        this.id = id;
        return this;
    }

    public TaskInfoBuilder withName(String name){
        this.name = name;
        return this;
    }
    public TaskInfoBuilder withStatus(TaskStatus status){
        this.status = status;
        return this;
    }
    public TaskInfoBuilder withType(TaskType type){
        this.type = type;
        return this;
    }
    public TaskInfoBuilder withAuthor(String author){
        this.author = author;
        return this;
    }
    public TaskInfoBuilder withAssignedTo(String assignedTo){
        this.assignedTo = assignedTo;
        return this;
    }
    public TaskInfoBuilder withAppointed(String appointed){
        this.appointed = appointed;
        return this;
    }
    public TaskInfoBuilder withCreated(LocalDateTime created){
        this.created = created;
        return this;
    }
    public TaskInfoBuilder withLastUpdated(LocalDateTime lastUpdated){
        this.lastUpdated = lastUpdated;
        return this;
    }

    public TaskInfo build() {
        TaskInfo task = new TaskInfo();
        task.setId(id);
        task.setName(name);
        task.setType(type);
        task.setStatus(status);
        task.setAuthor(author);
        task.setAssignedTo(assignedTo);
        task.setAppointed(appointed);
        task.setCreated(created);
        task.setLastUpdated(lastUpdated);
        return task;
    }

}
