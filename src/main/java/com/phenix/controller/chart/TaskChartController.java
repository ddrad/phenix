package com.phenix.controller.chart;

import com.phenix.datalayer.entity.Task;
import com.phenix.model.TaskInfo;
import com.phenix.model.TaskStatus;

import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import com.phenix.service.product.TaskService;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
public class TaskChartController implements Serializable {

    private final TaskService taskService;
    private PieChartModel livePieModel;
    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;

    private BiConsumer<Map<TaskStatus, Long>, TaskStatus> addToLivePieModel = (c, s) -> {
        livePieModel.getData().put(s.statusName(), c.get(s) == null ? 0 : c.get(s).intValue());
    };

    private BiConsumer<Map<String, Long>, TaskStatus> addToBarModel = (c, s) -> {
        livePieModel.getData().put(s.statusName(), c.get(s) == null ? 0 : c.get(s).intValue());
    };

    public TaskChartController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostConstruct
    public void init() {
        createLivePieModel();
        createBarModels();
    }

    private void createLivePieModel() {
        livePieModel = new PieChartModel();
        fetchDataToLivePieModel();
    }

    public PieChartModel getLivePieModel() {
        fetchDataToLivePieModel();
        livePieModel.setTitle("Tasks");
        livePieModel.setLegendPosition("ne");
        return livePieModel;
    }

    private void fetchDataToLivePieModel() {
        Map<TaskStatus, Long> collect = taskService.getAllTask()
                .stream()
                .collect(Collectors.groupingBy(TaskInfo::getStatus, Collectors.counting()));

        addToLivePieModel.accept(collect, TaskStatus.NEW);
        addToLivePieModel.accept(collect, TaskStatus.ACTIVATED);
        addToLivePieModel.accept(collect, TaskStatus.NOT_ACTIVATED);
        addToLivePieModel.accept(collect, TaskStatus.BLOCKED);
    }

    private void createBarModels() {
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();
        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Users");
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tasks");
        yAxis.setMin(0);
        yAxis.setMax(50);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries activated = new ChartSeries();
        activated.setLabel("Activated");
        fetchDataToBarModel(activated);
        model.addSeries(activated);
        return model;
    }

    private void fetchDataToBarModel(ChartSeries activated) {
        taskService.getAllTask()
                .stream()
                .collect(Collectors.groupingBy(TaskInfo::getAssignedTo, Collectors.counting()))
                .forEach((key, value) -> activated.set(key, value));
    }

    public BarChartModel getBarModel() {
        barModel.getSeries().stream().findFirst().ifPresent(this::fetchDataToBarModel);
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
}

