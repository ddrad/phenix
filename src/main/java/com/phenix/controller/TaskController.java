package com.phenix.controller;

import com.phenix.datalayer.entity.Task;

import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import com.phenix.model.TaskInfo;
import com.phenix.service.product.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean
@ViewScoped
public class TaskController implements Serializable {

    private List<TaskInfo> tasks;
    private TaskInfo selectedTask;
    private Task entity;

    @Autowired
    private TaskService taskService;

    @PostConstruct
    public void init() {
        tasks = taskService.getAllTask();
        entity = null;
    }

    public void updateTasks() {
        tasks = taskService.getAllTask();
    }

    public List<TaskInfo> getTasks() {
        return tasks;
    }

    public int getProductCount() {
        return tasks.size();
    }


    public TaskInfo getSelectedTask() {
        if(selectedTask != null && entity == null) {
            try {
                entity = taskService.openTaskDetails(selectedTask.getId());
            } catch (RuntimeException e) {
                FacesMessage msg = new FacesMessage("Error", "Closed");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }
            return taskService.toTaskInfo(entity);
        }
        else if(entity != null) {
            return taskService.toTaskInfo(entity);
        }
        return null;
    }

    public void closeTaskDetails() {
        if(entity != null) {
            taskService.closeTask(entity);
            entity = null;
            FacesMessage msg = new FacesMessage("Successful", "Closed");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void setSelectedTask(TaskInfo selectedTask) {
        this.selectedTask = selectedTask;
    }
}
