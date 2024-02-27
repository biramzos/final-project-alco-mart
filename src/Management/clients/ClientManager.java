package Management.clients;

import Management.admins.Admin;
import Management.common.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientManager implements Manager {

    @Override
    public List<Model> getList() {
        List<Model> clients = new ArrayList<>();
        String query = "SELECT * FROM clients WHERE role = " + Role.CLIENT.getId() ;
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
        Model client = null;
        String query = "SELECT * FROM users WHERE role = " + Role.CLIENT.getId() + " AND id = " + id;
        try (Connection con = DBConstants.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {
            while (res.next()) {
                client = new Client(
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
        return client;
    }

    @Override
    public boolean insert(Model model) {
        boolean isInserted = false;
        Client newClient = (Client) model;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO users (username, password, address, phone, role) VALUES ('")
                .append(newClient.getUsername()).append("','")
                .append(newClient.getPassword()).append("','")
                .append(newClient.getAddress()).append("','")
                .append(newClient.getPhone()).append("', ")
                .append(Role.CLIENT.getId()).append(")");
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
        Client newClient = (Client) model;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE users SET username = '")
                .append(newClient.getUsername()).append("', password = '")
                .append(newClient.getPassword()).append("', address = '")
                .append(newClient.getAddress()).append("', phone = '")
                .append(newClient.getPhone()).append("', role = ")
                .append(Role.CLIENT.getId()).append(" WHERE id = ").append(newClient.getId());
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
