package Notification_And_Alerts;

import java.util.List;

public class Customer {
    private String name;
    private String email; // or phone for SMS
    private List<Meal> upcomingMeals;

    public void addUpcomingMeal(Meal meal) {
        upcomingMeals.add(meal);
    }
}
