package Zahi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Task {
    private String id;
    private String description;
    private String requiredExpertise;
    private LocalDateTime dueTime;
    private String taskName;
    private String assignedChef;

    public Task(String description, LocalDateTime dueTime, String requiredExpertise) {
        this.id = "TASK-" + UUID.randomUUID().toString().substring(0, 6);
        this.description = description;
        this.requiredExpertise = requiredExpertise;
        this.taskName = taskName;
        this.assignedChef = assignedChef;
        this.dueTime = dueTime;
    }

    public String getRequiredExpertise() {
        return requiredExpertise;
    }

    public Object getDescription() {
        return description;
    }

    public LocalDateTime getDueTime() {
        return dueTime;
    }
}
