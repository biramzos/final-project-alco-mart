package Management.admins;

import Management.clients.Client;
import Management.common.DBConstants;
import Management.common.Manager;
import Management.common.Model;
import Management.common.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminManager implements Manager {
    @Override
    public List<Model> getList() {
        List<Model> clients = new ArrayList<>();
        String query = "SELECT * FROM users WHERE role = " + Role.ADMIN.getId() ;
        try (Connection con = DBConstants.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {
            while (res.next()) {
                clients.add(
                    new Client(
                        res.getInt("id"),
                        res.getString("username"),
                        res.getString("password"),
                        res.getString("address"),
                        res.getString("phone")
                    )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    @Override
    public Model get(int id) {
        Model admin = null;
        String query = "SELECT * FROM users WHERE role = " + Role.ADMIN.getId() + " AND id = " + id;
        try (Connection con = DBConstants.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {
            while (res.next()) {
                admin = new Client (
                    res.getInt("id"),
                    res.getString("username"),
                    res.getString("password"),
                    res.getString("address"),
                    res.getString("phone")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }

    @Override
    public boolean insert(Model model) {
        boolean isInserted = false;
        Admin newAdmin = (Admin) model;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO users (username, password, address, phone, role) VALUES ('")
                .append(newAdmin.getUsername()).append("','")
                .append(newAdmin.getPassword()).append("','")
                .append(newAdmin.getAddress()).append("','")
                .append(newAdmin.getPhone()).append("', ")
                .append(Role.ADMIN.getId()).append(")");
        try (Connection con = DBConstants.getConnection(); Statement st = con.createStatement()) {
            st.execute(query.toString());
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Model model) {
        boolean isUpdated = false;
        Admin newAdmin = (Admin) model;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE users SET username = '")
                .append(newAdmin.getUsername()).append("', password = '")
                .append(newAdmin.getPassword()).append("', address = '")
                .append(newAdmin.getAddress()).append("', phone = '")
                .append(newAdmin.getPhone()).append("', role = ")
                .append(Role.ADMIN.getId()).append(" WHERE id = ").append(newAdmin.getId());
        try (Connection con = DBConstants.getConnection(); Statement st = con.createStatement()) {
            st.execute(query.toString());
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        boolean isDeleted = false;
        String query = "DELETE FROM users WHERE id = " + id;
        try (Connection con = DBConstants.getConnection(); Statement st = con.createStatement()) {
            st.execute(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
