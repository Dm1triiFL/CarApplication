package org.example.repository;

import org.example.database.DatabaseConnection;
import org.example.entity.DealershipEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DealershipRepository {

    public void create(DealershipEntity dealership) {
        String sql = "INSERT INTO Dealership (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dealership.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DealershipEntity> findAll() {
        List<DealershipEntity> dealerships = new ArrayList<>();
        String sql = "SELECT * FROM Dealership";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DealershipEntity dealership = new DealershipEntity(rs.getString("name"));
                dealerships.add(dealership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealerships;
    }

    public Optional<DealershipEntity> findByName(String name) {
        String sql = "SELECT * FROM Dealership WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                DealershipEntity dealership = new DealershipEntity(rs.getString("name"));
                return Optional.of(dealership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(DealershipEntity dealership) {
        String sql = "UPDATE Dealership SET name = ? WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dealership.getName());
            stmt.setString(2, dealership.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String name) {
        String sql = "DELETE FROM Dealership WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
