package  com.phenix.service.product;

import com.phenix.datalayer.entity.Task;
import com.phenix.model.TaskStatus;
import com.phenix.model.TaskType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class TaskMockService {

    private List<Task> tasks = new ArrayList<>();

    @PostConstruct
    public void init() {
        tasks.add(new Task(1, "Task1", TaskType.DAFAULT, TaskStatus.ACTIVATED, "admin", "ddard", LocalDateTime.now()));
        tasks.add(new Task(2, "Task2", TaskType.DAFAULT, TaskStatus.NEW, "admin", "ddard", LocalDateTime.now()));
        tasks.add(new Task(3, "Task3", TaskType.DAFAULT, TaskStatus.ACTIVATED, "admin", "ddard", LocalDateTime.now()));
        tasks.add(new Task(4, "Task4", TaskType.DAFAULT, TaskStatus.NEW, "admin", "ddard", LocalDateTime.now()));
        tasks.add(new Task(5, "Task5", TaskType.DAFAULT, TaskStatus.ACTIVATED, "admin", "ddard", LocalDateTime.now()));
        tasks.add(new Task(6, "Task6", TaskType.DAFAULT, TaskStatus.NEW, "admin", "ddard", LocalDateTime.now()));
        tasks.add(new Task(7, "Task7", TaskType.DAFAULT, TaskStatus.ACTIVATED, "admin", "user1", LocalDateTime.now()));
        tasks.add(new Task(8, "Task8", TaskType.DAFAULT, TaskStatus.ACTIVATED, "admin", "ddard", LocalDateTime.now()));
        tasks.add(new Task(9, "Task9", TaskType.DAFAULT, TaskStatus.ACTIVATED, "admin", "user1", LocalDateTime.now()));
        tasks.add(new Task(10, "Task10", TaskType.DAFAULT, TaskStatus.ACTIVATED, "admin", "ddard", LocalDateTime.now()));
        tasks.add(new Task(11, "Task11", TaskType.DAFAULT, TaskStatus.BLOCKED, "admin", "user2", LocalDateTime.now()));
        tasks.add(new Task(12, "Task12", TaskType.DAFAULT, TaskStatus.BLOCKED, "admin", "user2", LocalDateTime.now()));

    }

    public List<Task> getAllProductMock() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
