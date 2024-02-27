package Management;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Buyer {
    private final BeverageManager beverageManager;

    public Buyer(BeverageManager beverageManager) {
        this.beverageManager = beverageManager;
    }

    public void browseProducts() {
        beverageManager.browseProducts();
    }

    public void makeOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the beverage you want to order:");
        int id = scanner.nextInt();

        String query = "INSERT INTO orders (beverage_id) VALUES (?)";
        try (PreparedStatement statement = beverageManager.getConnection().prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Your order has been placed.");
            } else {
                System.out.println("Error while placing the order.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}