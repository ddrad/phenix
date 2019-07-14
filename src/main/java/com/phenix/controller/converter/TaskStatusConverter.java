package com.phenix.controller.converter;

import com.phenix.model.TaskStatus;
import com.phenix.model.TaskType;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

/**
 * Created by Dmitry Azarov on 7/14/2019.
 */

@FacesConverter(value="taskStatusConverter")
public class TaskStatusConverter extends EnumConverter {

    public TaskStatusConverter() {
        super(TaskStatus.class);
    }

}
