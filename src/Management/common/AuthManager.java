package Management.common;

import Management.admins.AdminMenu;
import Management.clients.Client;
import Management.clients.ClientMenu;
import Management.products.AlcoMart;
import Management.products.AlcoMartManager;

import java.sql.*;
import java.util.Scanner;

public class AuthManager {

    public static User user = null;

    public static void signIn(Scanner scanner){
        boolean exit = false;
        while (!exit) {
            System.out.print("[username] ");
            String username = scanner.next();
            System.out.print("[password] ");
            String password = scanner.next();
            AlcoMartManager alcoMartManager = new AlcoMartManager();
            if (getFromDB(username, password)) {
                System.out.println("Successfully authorized!");
                exit = true;
                if (user.getRole() == Role.ADMIN) {
                    new AdminMenu().launchMenu(alcoMartManager, user.getId());
                } else {
                    new ClientMenu().launchMenu(alcoMartManager, user.getId());
                }
            } else {
                System.out.println("[-] Incorrect username or password!");
                System.out.println("[+] Do u wanna retype username and password again?");
                System.out.println("[1] Yeap");
                System.out.println("[2] No");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    exit = false;
                } else if (choice == 2) {
                    exit = true;
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        }
    }

    public static void signUp(Scanner scanner){
        User newUser = new User();
        System.out.print("[username] ");
        newUser.setUsername(scanner.next());
        System.out.print("[password] ");
        newUser.setPassword(scanner.next());
        System.out.print("[address] ");
        newUser.setAddress(scanner.next());
        System.out.print("[phone] ");
        newUser.setPhone(scanner.next());
        System.out.println("[choose ur role] ");
        System.out.println("[1] Admin.");
        System.out.println("[2] Client.");
        int role = scanner.nextInt();
        if (role == 1) {
            newUser.setRole(Role.ADMIN);
        } else if (role == 2) {
            newUser.setRole(Role.CLIENT);
        } else {
            System.out.println("Invalid choice! You're a client!");
            newUser.setRole(Role.CLIENT);
        }
        AlcoMartManager alcoMartManager = new AlcoMartManager();
        if (saveFromDB(newUser)) {
            System.out.println("Successfully authorized!");
            if (user.getRole() == Role.ADMIN) {
                new AdminMenu().launchMenu(alcoMartManager, user.getId());
            } else {
                new ClientMenu().launchMenu(alcoMartManager, user.getId());
            }
        }
    }

    private static boolean getFromDB(String username, String password) {
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        try (Connection con = DBConstants.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {
            while (res.next()) {
                AuthManager.user = new User(
                    res.getInt("id"),
                    res.getString("username"),
                    res.getString("password"),
                    res.getString("address"),
                    res.getString("phone"),
                    Role.fromID(res.getInt("role"))
                );
            }
            if (AuthManager.user != null) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    private static boolean saveFromDB(User user) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO users (username, password, address, phone, role) VALUES ('")
                .append(user.getUsername()).append("','")
                .append(user.getPassword()).append("','")
                .append(user.getAddress()).append("','")
                .append(user.getPhone()).append("', ")
                .append(user.getRole().getId()).append(")");
        try (Connection con = DBConstants.getConnection(); Statement st = con.createStatement()) {
            st.execute(query.toString());
            AuthManager.user = user;
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
