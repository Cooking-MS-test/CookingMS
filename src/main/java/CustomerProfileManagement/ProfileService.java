package CustomerProfileManagement;

import Notification_And_Alerts.Customer;

public class ProfileService {
    // Validate and save dietary info
    public boolean saveDietaryInfo(Customer customer, String preferences, String allergies) {
        if (preferences == null || allergies == null) {
            return false; // Reject null inputs
        }
        customer.setDietaryPreferences(preferences);
        customer.setAllergies(allergies);
        return true;
    }
}
