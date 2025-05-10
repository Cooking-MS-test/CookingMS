package test;

public class customer {
    private String name;

    public customer(String name) {
        this.name = name;
    }

    public void receiveReminder(String message) {
        System.out.println(name + " received reminder: " + message);
    }
}
