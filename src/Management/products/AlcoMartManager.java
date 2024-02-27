package Management.products;

import Management.clients.Client;
import Management.common.*;
import Management.common.bills.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlcoMartManager implements Manager {
    @Override
    public List<Model> getList() {
        List<Model> alcohols = new ArrayList<>();
        String query = "SELECT * FROM alcoholic_beverages";
        try (Connection con = DBConstants.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {
            while (res.next()) {
                alcohols.add(
                    new AlcoMart(
                        res.getInt("id"),
                        res.getString("name"),
                        AlcoCategory.valueOf(res.getString("category")),
                        res.getDouble("volume"),
                        res.getInt("year"),
                        res.getDouble("price"),
                        res.getInt("percent")
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
        String query = "SELECT * FROM alcoholic_beverages WHERE id = " + id;
        try (Connection con = DBConstants.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {
            while (res.next()) {
                alcohol = new AlcoMart(
                    res.getInt("id"),
                    res.getString("name"),
                    AlcoCategory.valueOf(res.getString("category")),
                    res.getDouble("volume"),
                    res.getInt("year"),
                    res.getDouble("price"),
                    res.getInt("percent")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alcohol;
    }

    @Override
    public boolean insert(Model model) {
        AlcoMart newAlcohol = (AlcoMart) model;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO alcoholic_beverages (name, category, volume, year, price, percent) VALUES ('")
                .append(newAlcohol.getName()).append("','")
                .append(newAlcohol.getCategory().name()).append("', ")
                .append(newAlcohol.getVolume()).append(", ")
                .append(newAlcohol.getYear()).append(", ")
                .append(newAlcohol.getPrice()).append(", ")
                .append(newAlcohol.getPercent()).append(") ");
        try (Connection con = DBConstants.getConnection(); Statement st = con.createStatement()) {
            st.execute(query.toString());
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Model model) {
        AlcoMart newAlcohol = (AlcoMart) model;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE alcoholic_beverages SET name = '")
                .append(newAlcohol.getName()).append("', category = '")
                .append(newAlcohol.getCategory().name()).append("', volume = ")
                .append(newAlcohol.getVolume()).append(", year = ")
                .append(newAlcohol.getYear()).append(", price = ")
                .append(newAlcohol.getPrice()).append(", percent = ")
                .append(newAlcohol.getPercent()).append(" WHERE id = ").append(newAlcohol.getId());
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
        String query = "DELETE FROM alcoholic_beverages WHERE id = " + id;
        try (Connection con = DBConstants.getConnection(); Statement st = con.createStatement()) {
            st.execute(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean billInsert(Model model) {
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

    public List<Model> getHistoryProducts(int user_id) {
        List<Model> alcohols = new ArrayList<>();
        String query = "SELECT * FROM alcoholic_beverages ab LEFT JOIN bills b ON b.alcohol_id = ab.id " +
                " LEFT JOIN users u ON b.user_id = u.id WHERE u.id = " + user_id;
        try (Connection con = DBConstants.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {
            while (res.next()) {
                alcohols.add(
                    new AlcoMart(
                        res.getInt("id"),
                        res.getString("name"),
                        AlcoCategory.valueOf(res.getString("category")),
                        res.getDouble("volume"),
                        res.getInt("year"),
                        res.getDouble("price"),
                        res.getInt("percent")
                    )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alcohols;
    }
}
