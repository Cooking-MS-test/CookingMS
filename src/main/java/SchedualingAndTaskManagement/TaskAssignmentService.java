package SchedualingAndTaskManagement;

import Notification_And_Alerts.Chef;
import Notification_And_Alerts.NotificationService;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TaskAssignmentService {
    private NotificationService notificationService;
    private List<Chef> chefs;

    public TaskAssignmentService(NotificationService notificationService, List<Chef> chefs) {
        this.notificationService = notificationService;
        this.chefs = chefs;
    }

    public Optional<Chef> assignTask(Task task) {
        return chefs.stream()
                .filter(chef -> chef.hasExpertise(task.getRequiredExpertise()))
                .filter(Chef::canTakeTask)
                .min(Comparator.comparingInt(Chef::getCurrentWorkload))
                .map(selectedChef -> {
                    selectedChef.assignTask(task);
                    notifyChef(selectedChef, task);
                    return selectedChef;
                });
    }

    private void notifyChef(Chef chef, Task task) {
        String message = String.format(
                "You have been assigned to: %s (Due: %s)",
                task.getDescription(),
                task.getDueTime().format(DateTimeFormatter.ofPattern("HH:mm"))
        );
        notificationService.sendNotification(chef.getContactInfo()+ message);
    }
}
