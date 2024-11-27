package org.example.repository;

import org.example.entity.DealerEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealerRepository {
    private final Connection connection;

    public DealerRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(DealerEntity dealer) {
        String sql = "INSERT INTO dealership (name, address) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dealer.getName());
            statement.setString(2, dealer.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DealerEntity read(long id) {
        String sql = "SELECT * FROM dealership WHERE id = ?";
        DealerEntity dealer = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                dealer = new DealerEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealer;
    }

    public void update(DealerEntity dealer) {
        String sql = "UPDATE dealership SET name = ?, address = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dealer.getName());
            statement.setString(2, dealer.getAddress());
            statement.setLong(3, dealer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        String sql = "DELETE FROM dealership WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DealerEntity> findAll() {
        List<DealerEntity> dealers = new ArrayList<>();
        String sql = "SELECT * FROM dealership";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                DealerEntity dealer = new DealerEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address")
                );
                dealers.add(dealer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealers;
    }

    public List<DealerEntity> findByCondition(String condition) {
        List<DealerEntity> dealers = new ArrayList<>();
        String sql = "SELECT * FROM dealership WHERE " + condition;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                DealerEntity dealer = new DealerEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address")
                );
                dealers.add(dealer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealers;
    }
}
