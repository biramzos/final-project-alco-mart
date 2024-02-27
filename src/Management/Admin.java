package Management;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
    private final BeverageManager beverageManager;
    private final Connection connection;


    public Admin(BeverageManager beverageManager, Connection connection) {
        this.beverageManager = beverageManager;
        this.connection = connection;
    }

    public void adminMenu() {
        beverageManager.browseProducts();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Management.Admin menu:");
            System.out.println("1) Add a new beverage");
            System.out.println("2) Edit an existing beverage");
            System.out.println("3) Delete a beverage");
            System.out.println("4) Exit admin menu");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addNewBeverage();
                    break;
                case 2:
                    editBeverage();
                    break;
                case 3:
                    deleteBeverage();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addNewBeverage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the manufacturer of the new beverage:");
        String name = scanner.nextLine();
        System.out.println("Enter the category of the new beverage:");
        String category = scanner.nextLine();
        System.out.println("Enter the volume of the new beverage:");
        BigDecimal volume = scanner.nextBigDecimal();
        System.out.println("Enter the year of the new beverage:");
        int year = scanner.nextInt();
        System.out.println("Enter the percentage of the new beverage:");
        BigDecimal percentage = scanner.nextBigDecimal();
        System.out.println("Enter the price of the new beverage:");
        BigDecimal price = scanner.nextBigDecimal();

        String query = "INSERT INTO alcoholic_beverages (name, category, volume, year, percent, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = beverageManager.getConnection().prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, category);
            statement.setBigDecimal(3, volume);
            statement.setInt(4, year);
            statement.setBigDecimal(5, percentage);
            statement.setBigDecimal(6, price);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Beverage added successfully.");
            } else {
                System.out.println("Error while adding beverage.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editBeverage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the beverage you want to edit:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter the new manufacturer:");
        String newName = scanner.nextLine();
        System.out.println("Enter the new category:");
        String newCategory = scanner.nextLine();
        System.out.println("Enter the new volume:");
        BigDecimal newVolume = scanner.nextBigDecimal();
        System.out.println("Enter the new year:");
        int newYear = scanner.nextInt();
        System.out.println("Enter the new percent:");
        BigDecimal newPercentage = scanner.nextBigDecimal();
        System.out.println("Enter the new price:");
        BigDecimal newPrice = scanner.nextBigDecimal();

        String query = "UPDATE alcoholic_beverages SET name = ?, category = ?, volume = ?, year = ?,percent = ?, price = ? WHERE id = ?";
        try (PreparedStatement statement = beverageManager.getConnection().prepareStatement(query)) {
            statement.setString(1, newName);
            statement.setString(2, newCategory);
            statement.setBigDecimal(3, newVolume);
            statement.setInt(4, newYear);
            statement.setBigDecimal(5, newPercentage);
            statement.setBigDecimal(6, newPrice);
            statement.setInt(7, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Beverage successfully updated.");
            } else {
                System.out.println("Error updating beverage.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void deleteBeverage() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the ID of the beverage you want to delete:");
            int id = scanner.nextInt();

            // First, delete the associated orders
            String deleteOrdersQuery = "DELETE FROM orders WHERE beverage_id = ?";
            try (PreparedStatement deleteOrdersStatement = connection.prepareStatement(deleteOrdersQuery)) {
                deleteOrdersStatement.setInt(1, id);
                deleteOrdersStatement.executeUpdate();
            }

            // Then, delete the beverage itself
            String deleteBeverageQuery = "DELETE FROM alcoholic_beverages WHERE id = ?";
            try (PreparedStatement deleteBeverageStatement = connection.prepareStatement(deleteBeverageQuery)) {
                deleteBeverageStatement.setInt(1, id);
                int rowsAffected = deleteBeverageStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Beverage successfully deleted.");
                } else {
                    System.out.println("Failed to delete beverage.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}