package Zahi;

public class StockCheckResult {
    private final boolean notificationSent;
    private final String notificationMessage;
    private final String systemMessage;

    public StockCheckResult(boolean notificationSent,
                            String notificationMessage,
                            String systemMessage) {
        this.notificationSent = notificationSent;
        this.notificationMessage = notificationMessage;
        this.systemMessage = systemMessage;
    }

    // Getters
    public boolean isNotificationSent() { return notificationSent; }
    public String getNotificationMessage() { return notificationMessage; }
    public String getSystemMessage() { return systemMessage; }
}
