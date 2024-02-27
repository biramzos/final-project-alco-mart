package Management;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ClientList {
    private final ClientManager clientListManager;
    private Connection connection;

    public ClientList(ClientManager clientListManager) {
        this.clientListManager = clientListManager;
        this.connection = connection;
    }

    public void clientMenu() {
        clientListManager.displayClients();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Client List Admin Menu:");
            System.out.println("1) Add a new client");
            System.out.println("2) Edit an existing client");
            System.out.println("3) Delete a client");
            System.out.println("4) Exit admin menu");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addNewClient();
                    break;
                case 2:
                    editClient();
                    break;
                case 3:
                    deleteClient();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void addNewClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the new client:");
        String name = scanner.nextLine();
        System.out.println("Enter the address of the new client:");
        String address = scanner.nextLine();
        System.out.println("Enter the phone number of the new client:");
        BigDecimal phoneNumber = scanner.nextBigDecimal();

        String query = "INSERT INTO client (name, address, phone_number) VALUES (?, ?, ?)";
        try (PreparedStatement statement = clientListManager.getConnection().prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setBigDecimal(3, phoneNumber);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Client added successfully.");
            } else {
                System.out.println("Error while adding client.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the client you want to edit:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter the new name:");
        String newName = scanner.nextLine();
        System.out.println("Enter the new address:");
        String newAddress = scanner.nextLine();
        System.out.println("Enter the new phone number:");
        String newPhoneNumber = scanner.nextLine();

        String query = "UPDATE client SET name = ?, address = ?, phone_number = ? WHERE id = ?";
        try (PreparedStatement statement = clientListManager.getConnection().prepareStatement(query)) {
            statement.setString(1, newName);
            statement.setString(2, newAddress);
            statement.setString(3, newPhoneNumber);
            statement.setInt(4, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Client successfully updated.");
            } else {
                System.out.println("Error updating client.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteClient() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the ID of the client you want to delete:");
            int id = scanner.nextInt();

            String deleteClientQuery = "DELETE FROM client WHERE id = ?";
            try (PreparedStatement deleteClientStatement = connection.prepareStatement(deleteClientQuery)) {
                deleteClientStatement.setInt(1, id);
                int rowsAffected = deleteClientStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Client successfully deleted.");
                } else {
                    System.out.println("Failed to delete client.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
