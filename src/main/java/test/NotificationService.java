package test;

public class NotificationService {
    public void notifyKitchenManager(String ingredient, String message) {
        System.out.println(message);
    }

    public void notifyChef(String message) {
        System.out.println("Chef notification: " + message);
    }
}
