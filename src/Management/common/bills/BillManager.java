package Management.common.bills;

import Management.common.AlcoCategory;
import Management.common.DBConstants;
import Management.common.Manager;
import Management.common.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillManager implements Manager {
    @Override
    public List<Model> getList() {
        List<Model> alcohols = new ArrayList<>();
        String query = "SELECT * FROM bills";
        try (Connection con = DBConstants.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {
            while (res.next()) {
                alcohols.add(
                    new Bill(
                        res.getInt("id"),
                        res.getInt("user_id"),
                        res.getInt("alcohol_id")
                    )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alcohols;
    }

    @Override
    public Model get(int id) {
        Model alcohol = null;
        String query = "SELECT * FROM bills WHERE id = " + id;
        try (Connection con = DBConstants.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {
            while (res.next()) {
                alcohol = new Bill(
                    res.getInt("id"),
                    res.getInt("user_id"),
                    res.getInt("alcohol_id")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alcohol;
    }

    @Override
    public boolean insert(Model model) {
        boolean isInserted = false;
        Bill newAlcohol = (Bill) model;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO bills (user_id, alcohol_id) VALUES (")
                .append(newAlcohol.getUserId()).append(",")
                .append(newAlcohol.getAlcoholId()).append(")");
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
        Bill newAlcohol = (Bill) model;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE bills SET user_id = ")
                .append(newAlcohol.getUserId()).append(", alcohol_id = ")
                .append(newAlcohol.getAlcoholId()).append(" WHERE id = ").append(newAlcohol.getId());
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
        String query = "DELETE FROM bills WHERE id = " + id;
        try (Connection con = DBConstants.getConnection(); Statement st = con.createStatement()) {
            st.execute(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
