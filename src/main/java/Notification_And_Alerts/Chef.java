package Notification_And_Alerts;

import java.util.List;

public class Chef {
    private String name;
    private String contactInfo;
    private List<Meal> assignedTasks;

    public void addCookingTask(Meal meal) {
        assignedTasks.add(meal);
    }
}
