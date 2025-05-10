package test;

import java.util.ArrayList;
import java.util.List;

public class taskAssignmentSystem {
    private List<chef> chefs;

    public taskAssignmentSystem() {
        this.chefs = new ArrayList<>();
    }

    public void assignTask(String task, String requiredExpertise) {
        chef availableChef = findAvailableChef(requiredExpertise);
        if (availableChef != null) {
            availableChef.receiveNotification("You have been assigned to: " + task);
            System.out.println("Task '" + task + "' assigned to " + availableChef.getName());
        } else {
            System.out.println("No available chef with " + requiredExpertise + " expertise");
        }
    }

    private chef findAvailableChef(String expertise) {
        // Simplified logic - in real system would check workload and expertise
        for (chef chef : chefs) {
            // Assume all chefs have the required expertise for this example
            return chef;
        }
        return null;
    }

    public void addChef(chef chef) {
        chefs.add(chef);
    }
}
