package Notification_And_Alerts;

import SchedualingAndTaskManagement.Task;

import java.util.ArrayList;
import java.util.List;

public class Chef {
    private String name;
    private String contactInfo;


    private String expertise;
    private int currentWorkload; // Number of active tasks
    private List<Task> assignedTasks;
    private List<Meal> assignedMeals;
    public Chef(String name, String expertise, String contactInfo) {
        this.name = name;
        this.expertise = expertise;
        this.contactInfo = contactInfo;
        this.assignedTasks = new ArrayList<>();
        this.currentWorkload = 0;
    }

    public boolean canTakeTask() {
        return currentWorkload < 3; // Max 3 tasks per chef
    }

    public void assignTask(Task task) {
        this.assignedTasks.add(task);
        this.currentWorkload++;
    }
    public boolean hasExpertise(String requiredExpertise) {
        return this.expertise.equalsIgnoreCase(requiredExpertise);
    }
    public void addCookingTask(Meal meal) {
        assignedMeals.add(meal);
    }
    public void viewCustomerDietaryInfo(Customer customer) {
        System.out.println("Customer Dietary Info:\n" + customer.getDietarySummary());
    }

    public String getName() {
        return name;
    }

    public int getCurrentWorkload() {
        return currentWorkload;
    }

    public Object getContactInfo() {
        return contactInfo;
    }
}
