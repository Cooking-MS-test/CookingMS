package test;

public class chef {
    private String name;

    public chef(String name) {
        this.name = name;
    }

    public void receiveNotification(String message) {
        System.out.println(name + " received notification: " + message);
    }

    public String getName() {
        return name;
    }
}
