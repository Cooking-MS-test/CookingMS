package models;

import Notification_And_Alerts.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Admin {
private String Name;
private List<Customer> customers=new ArrayList<Customer>();

public Admin(String name) {
    Name = name;
}
public Admin(String name, Customer customer){
    Name = name;
    this.customers.add(customer);
}
public String getName() {
    return Name;
}
public List<Customer> getCustomers() {
    return customers;
}
public void setName(String name) {
    Name = name;
}
public void addCustomer(Customer customer){
    customers.add(customer);
}
public List<String> getPastOrders() {
   List<String> allOrders = new ArrayList<>();
    for (Customer customerrrr : customers) {
        if (!customerrrr.getPastOrders().isEmpty()) {

            allOrders= Stream.concat(allOrders.stream(), customerrrr.getPastOrders().stream())
                    .collect(Collectors.toList());
        }

    }
    return allOrders;
}




}
// PUSHING
