package Management.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConstants {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "1607";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/task";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}
