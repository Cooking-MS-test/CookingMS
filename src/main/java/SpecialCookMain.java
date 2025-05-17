import Billing_System.InvoiceService;
import Billing_System.ReportService;
import InventoryAndSupplierManagement.InventoryManager;
import InventoryAndSupplierManagement.SupplierAPIService;
import Notification_And_Alerts.*;
import Zahi.*;
import models.Admin;
import models.Ingredient;
import models.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class SpecialCookMain {
    private static final String INVALID_CHOICE_MSG = "Invalid choice!";
    private static final String ENTER_CUSTOMER_NAME_PROMPT = "Enter customer name: ";

    private static Scanner scanner = new Scanner(System.in);
    private static List<Customer> customers = new ArrayList<>();
    private static List<Chef> chefs = new ArrayList<>();
    private static Admin admin = new Admin("System Admin");
    private static List<Order> allOrders = new ArrayList<>();
    private static List<Meal> availableMeals = new ArrayList<>(); // Meals added by admin

    public static void main(String[] args) {
        initializeSampleData();
        boolean running = true;

        while (running) {
            System.out.println("\n===== Special Cook Management System =====");
            System.out.println("1. Admin");
            System.out.println("2. Chef");
            System.out.println("3. Customer");
            System.out.println("4. Exit");
            System.out.print("Select your role: ");

            int roleChoice = Integer.parseInt(scanner.nextLine());

            switch (roleChoice) {
                case 1 -> adminMenu();
                case 2 -> chefMenu();
                case 3 -> customerMenu();
                case 4 -> {
                    System.out.println("Exiting system...");
                    running = false;
                }
                default -> System.out.println(INVALID_CHOICE_MSG);

            }
        }
    }

    private static void initializeSampleData() {
        // Sample admin-added meals
        availableMeals.add(new Meal("Vegetarian Pasta", 12.99));
        availableMeals.add(new Meal("Vegan Burger", 15.99));
        availableMeals.add(new Meal("Gluten-Free Dessert", 8.99));

        // Sample customer
        Customer customer = new Customer("John Doe", "john@example.com");
        customer.setDietaryPreferences("Vegetarian");
        customers.add(customer);

        // Sample chef
        Chef chef = new Chef("Gordon Ramsay", "Gourmet", 0);
        chefs.add(chef);
    }

    private static void adminMenu() {

        while (true) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Add New Customer");
            System.out.println("2. Add New Chef");
            System.out.println("3. Add New Meal");
            System.out.println("4. View All Customers");
            System.out.println("5. View All Chefs");
            System.out.println("6. View All Meals");
            System.out.println("7. View Financial Report");
            System.out.println("8. Back to Main Menu");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addNewCustomer();
                case 2 -> addNewChef();
                case 3 -> addNewMeal();
                case 4 -> viewAllCustomers();
                case 5 -> viewAllChefs();
                case 6 -> viewAllMeals();
                case 7 -> generateFinancialReport();
                case 8 -> { return; }
                default -> System.out.println(INVALID_CHOICE_MSG);

            }
        }
    }

    private static void addNewCustomer() {
        System.out.print(ENTER_CUSTOMER_NAME_PROMPT);
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        Customer newCustomer = new Customer(name, email);
        customers.add(newCustomer);
        admin.addCustomer(newCustomer);
        System.out.println("Customer added successfully!");
    }

    private static void addNewChef() {
        System.out.print("Enter chef name: ");
        String name = scanner.nextLine();
        System.out.print("Enter chef expertise: ");
        String expertise = scanner.nextLine();

        Chef newChef = new Chef(name, expertise, 0);
        chefs.add(newChef);
        System.out.println("Chef added successfully!");
    }

    private static void addNewMeal() {
        System.out.print("Enter meal name: ");
        String name = scanner.nextLine();
        System.out.print("Enter meal price: ");
        double price = Double.parseDouble(scanner.nextLine());

        availableMeals.add(new Meal(name, price));
        System.out.println("Meal added successfully!");
    }

    private static void viewAllCustomers() {
        System.out.println("\nAll Customers:");
        customers.forEach(c -> System.out.println(c.getName() + " - " + c.getEmail()));
    }

    private static void viewAllChefs() {
        System.out.println("\nAll Chefs:");
        chefs.forEach(c -> System.out.println(c.getName() + " - " + c.getExpertise()));
    }

    private static void viewAllMeals() {
        System.out.println("\nAvailable Meals:");
        availableMeals.forEach(m -> System.out.printf("%-20s $%.2f%n", m.getName(), m.getPrice()));
    }

    private static void generateFinancialReport() {
        ReportService reportService = new ReportService();
        String report = reportService.generateFinancialReport("Monthly", allOrders);
        reportService.sendReportToAdmin(report);
        System.out.println("\n" + report);
    }

    private static void chefMenu() {
        System.out.print("Enter chef name: ");
        String chefName = scanner.nextLine();

        Chef currentChef = chefs.stream()
                .filter(c -> c.getName().equalsIgnoreCase(chefName))
                .findFirst()
                .orElse(null);

        if (currentChef == null) {
            System.out.println("Chef not found!");
            return;
        }

        while (true) {
            System.out.println("\n===== CHEF MENU =====");
            System.out.println("1. View Assigned Tasks");
            System.out.println("2. View Customer Dietary Info");
            System.out.println("3. Back to Main Menu");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println("\nAssigned Tasks:");
                    System.out.println("Current workload: " + currentChef.getCurrentWorkload());
                    // Task viewing logic would go here
                }
                case 2 -> {
                    System.out.print(ENTER_CUSTOMER_NAME_PROMPT);
                    String customerName = scanner.nextLine();
                    Customer customer = customers.stream()
                            .filter(c -> c.getName().equalsIgnoreCase(customerName))
                            .findFirst()
                            .orElse(null);

                    if (customer != null) {
                        currentChef.viewCustomerDietaryInfo(customer);
                    } else {
                        System.out.println("Customer not found!");
                    }
                }
                case 3 -> { return; }
                default -> System.out.println(INVALID_CHOICE_MSG);

            }
        }
    }

    private static void customerMenu() {
        System.out.print(ENTER_CUSTOMER_NAME_PROMPT);
        String customerName = scanner.nextLine();

        Customer currentCustomer = customers.stream()
                .filter(c -> c.getName().equalsIgnoreCase(customerName))
                .findFirst()
                .orElse(null);

        if (currentCustomer == null) {
            System.out.println("Customer not found! Only existing customers can order.");
            return;
        }

        while (true) {
            System.out.println("\n===== CUSTOMER MENU =====");
            System.out.println("1. View Available Meals");
            System.out.println("2. Place Order");
            System.out.println("3. View Past Orders");
            System.out.println("4. Set Dietary Preferences");
            System.out.println("5. Back to Main Menu");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> viewAvailableMeals();
                case 2 -> placeOrder(currentCustomer);
                case 3 -> viewPastOrders(currentCustomer);
                case 4 -> setDietaryPreferences(currentCustomer);
                case 5 -> { return; }
                default -> System.out.println(INVALID_CHOICE_MSG);

            }
        }
    }

    private static void viewAvailableMeals() {
        System.out.println("\nAvailable Meals:");
        System.out.printf("%-5s %-20s %-10s%n", "No.", "Meal Name", "Price");
        System.out.println("----------------------------------");
        for (int i = 0; i < availableMeals.size(); i++) {
            Meal meal = availableMeals.get(i);
            System.out.printf("%-5d %-20s $%-10.2f%n", i+1, meal.getName(), meal.getPrice());
        }
    }

    private static void placeOrder(Customer customer) {
        viewAvailableMeals();
        System.out.print("\nEnter meal number to order: ");
        int mealChoice = Integer.parseInt(scanner.nextLine()) - 1;

        if (mealChoice < 0 || mealChoice >= availableMeals.size()) {
            System.out.println("Invalid meal selection!");
            return;
        }

        Meal selectedMeal = availableMeals.get(mealChoice);
        Order newOrder = new Order("ORD-" + (allOrders.size() + 1),
                customer, selectedMeal.getName(), selectedMeal.getPrice());

        allOrders.add(newOrder);
        customer.addOrder(newOrder);

        // Generate invoice
        InvoiceService invoiceService = new InvoiceService();
        String invoice = invoiceService.generateInvoice(newOrder);
        invoiceService.sendInvoice(customer, invoice);
        System.out.println("Order placed successfully!");
    }

    private static void viewPastOrders(Customer customer) {
        System.out.println("\nPast Orders:");
        List<String> pastOrders = customer.getPastOrders();
        if (pastOrders.isEmpty()) {
            System.out.println("No past orders");
        } else {
            pastOrders.forEach(System.out::println);
        }
    }

    private static void setDietaryPreferences(Customer customer) {
        System.out.print("Enter dietary preferences: ");
        String preferences = scanner.nextLine();
        customer.setDietaryPreferences(preferences);
        System.out.println("Preferences updated!");
    }
}