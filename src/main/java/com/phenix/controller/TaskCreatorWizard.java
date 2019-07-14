package com.phenix.controller;

import com.phenix.datalayer.entity.Task;
import com.phenix.model.TaskStatus;
import com.phenix.model.TaskType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import com.phenix.service.product.TaskService;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ManagedBean
@ViewScoped
public class TaskCreatorWizard implements Serializable {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskController controller;

    private Task task;
    private List<TaskType> taskTypes;
    private boolean skip;

    @PostConstruct()
    public void init() {
        task = new Task();
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void save() {
        createTask();
        FacesMessage msg = new FacesMessage("Successful", "Created :" + task.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        reload();
    }

    private void reload() {
        task = new Task();
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (event.getNewStep().equals("assigned")) {
            task.setStatus(TaskStatus.NEW);
        }
        if (event.getNewStep().equals("confirm")) {
            task.setAuthor(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public List<TaskType> getTaskTypes() {
        return Arrays.asList(TaskType.values());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createTask() {
        task.setCreated(LocalDateTime.now());
        taskService.save(task);
        controller.updateTasks();
    }
}

