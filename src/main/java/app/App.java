package app;

import app.config.AppConfig;
import app.dao.CustomerDAO;
import app.model.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerDAO dao = context.getBean(CustomerDAO.class);

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===CUSTOMER MENU====");
            System.out.println("1. Add customer");
            System.out.println("2. Find by id");
            System.out.println("3. Show all");
            System.out.println("4. Update");
            System.out.println("5. Delete");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.print("Full name: ");
                    String name = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("SSN: ");
                    String ssn = sc.nextLine();

                    Customer saved = dao.save(new Customer(null, name, email, ssn));
                    System.out.println("Customer added successfully: " + saved);
                }

                case 2 -> {
                    System.out.print("Enter ID: ");
                    Long id = sc.nextLong();
                    sc.nextLine();
                    Optional<Customer> customer = dao.findById(id);
                    System.out.println(customer.map(Customer::toString).orElse("Customer not found"));
                }

                case 3 -> {
                    dao.findAll().forEach(System.out::println);
                }

                case 4 -> {
                    System.out.print("Enter ID: ");
                    Long id = sc.nextLong();
                    sc.nextLine();

                    System.out.print("New name: ");
                    String name = sc.nextLine();

                    System.out.print("New email: ");
                    String email = sc.nextLine();

                    System.out.print("New SSN: ");
                    String ssn = sc.nextLine();
                    Customer customer = new Customer(id, name, email, ssn);
                    System.out.println(dao.update(customer)? "Customer updated successfully" : "Customer not found");
                }

                case 5 -> {
                    System.out.print("ID: ");
                    Long id = sc.nextLong();
                    System.out.println(dao.delete(id)? "Customer deleted!":"Customer not found");
                }

                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }

                default -> System.out.println("Wrong option");
            }
        }
    }
}