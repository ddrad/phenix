package com.phenix.datalayer.entity;

import com.phenix.model.TaskStatus;
import com.phenix.model.TaskType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskType type;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @NotNull
    private String author;
    private String assignedTo;
    private String appointed;
    @NotNull
    private LocalDateTime created;
    private LocalDateTime lastUpdated;
    private boolean executeNow;
    @Version
    private Integer version;


    public Task() {}

    public Task(long id, String name, TaskType type, TaskStatus status, String author, String assignedTo, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.author = author;
        this.assignedTo = assignedTo;
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getAppointed() {
        return appointed;
    }

    public void setAppointed(String appointed) {
        this.appointed = appointed;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean isExecuteNow() {
        return executeNow;
    }

    public void setExecuteNow(boolean executeNow) {
        this.executeNow = executeNow;
    }
}
