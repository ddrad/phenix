package com.phenix.controller.converter;

import com.phenix.model.TaskType;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

/**
 * Created by Dmitry Azarov on 7/14/2019.
 */

@FacesConverter(value="taskTypeConverter")
public class TaskTypeConverter extends EnumConverter {

    public TaskTypeConverter() {
        super(TaskType.class);
    }

}
