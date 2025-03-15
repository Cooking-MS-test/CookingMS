package models;

public class Chef {
    public void viewCustomerPreferences(Customer customer) {
        System.out.println("Customer: " + customer.getName());
        System.out.println("Dietary Preferences: " + customer.getDietaryPreferences());
        System.out.println("Allergies: " + customer.getAllergies());
    }
}
