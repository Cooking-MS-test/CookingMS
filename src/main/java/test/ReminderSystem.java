package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReminderSystem {
    private List<customer> customers;
    private List<chef> chefs;

    public ReminderSystem() {
        this.customers = new ArrayList<>();
        this.chefs = new ArrayList<>();
    }

    public void addCustomer(customer customer) {
        customers.add(customer);
    }

    public void addChef(chef chef) {
        chefs.add(chef);
    }

    public void sendDeliveryReminder(String meal, LocalDate deliveryDate) {
        String message = String.format("Reminder: Your %s will be delivered on %s",
                meal, deliveryDate.toString());
        for (customer customer : customers) {
            customer.receiveReminder(message);
        }
    }

    public void sendCookingTaskNotification(String meal, LocalDate cookingDate) {
        String message = String.format("Cooking Task: Prepare %s on %s",
                meal, cookingDate.toString());
        for (chef chef : chefs) {
            chef.receiveNotification(message);
        }
    }
}
