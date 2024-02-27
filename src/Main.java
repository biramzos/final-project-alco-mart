import Management.Admin;
import Management.BeverageManager;
import Management.Buyer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "0000";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/project";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            System.out.println("Welcome!");
            System.out.println("Choose your role:");
            System.out.println("1) Management.Buyer");
            System.out.println("2) Management.Admin");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1 || choice == 2) {
                BeverageManager beverageManager = new BeverageManager(connection);
                if (choice == 1) {
                    Buyer buyer = new Buyer(beverageManager);
                    buyer.browseProducts();
                    buyer.makeOrder();
                } else if (choice == 2) {
                    Admin admin = new Admin(beverageManager, connection);
                    admin.adminMenu();
                }
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}