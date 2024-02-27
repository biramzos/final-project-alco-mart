package Management;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BeverageManager {
    private final Connection connection;

    public BeverageManager(Connection connection) {
        this.connection = connection;
    }

    public void browseProducts() {
        String query = "SELECT id, name, category, volume, year, percent , price FROM alcoholic_beverages";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            System.out.println("Available beverages:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Manufacturer: " + resultSet.getString("name") +
                        ", Category: " + resultSet.getString("category") +
                        ", Volume: " + resultSet.getBigDecimal("volume") +
                        "L, Year: " + resultSet.getInt("year") +
                        " year, Percentage: " + resultSet.getBigDecimal("percent") +
                        "%, Price: " + resultSet.getBigDecimal("price") + "tg");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}