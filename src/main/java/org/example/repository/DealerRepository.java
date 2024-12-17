package org.example.repository;

import org.example.database.DatabaseConnection;
import org.example.entity.DealerEntity;
import org.example.entity.CarEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealerRepository {

    public void insertDealer(DealerEntity dealer) {
        String sql = "INSERT INTO Dealership (name) VALUES (?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dealer.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DealerEntity getDealer(String name) {
        String sql = "SELECT * FROM Dealership WHERE name = ?";
        DealerEntity dealer = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                dealer = new DealerEntity();
                dealer.setName(resultSet.getString("name"));
                dealer.setCars(getCarsByDealerName(name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealer;
    }

    public List<DealerEntity> getAllDealers() {
        String sql = "SELECT * FROM Dealership";
        List<DealerEntity> dealers = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                DealerEntity dealer = new DealerEntity();
                dealer.setName(resultSet.getString("name"));
                dealer.setCars(getCarsByDealerName(dealer.getName()));
                dealers.add(dealer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealers;
    }

    public void updateDealer(DealerEntity dealer) {
        String sql = "UPDATE Dealership SET name = ? WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dealer.getName());
            statement.setString(2, dealer.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDealer(String name) {
        String sql = "DELETE FROM Dealership WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<CarEntity> getCarsByDealerName(String dealershipName) {
        List<CarEntity> cars = new ArrayList<>();
        String sql = "SELECT * FROM Car WHERE dealershipName = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dealershipName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CarEntity car = new CarEntity();
                car.setId(resultSet.getInt("id"));
                car.setCarModelId(resultSet.getInt("carModelId"));
                car.setDealershipName(resultSet.getString("dealershipName"));
                car.setState(resultSet.getString("state"));
                car.setConfiguration(resultSet.getString("configuration"));
                car.setColor(resultSet.getString("color"));
                car.setPrice(resultSet.getDouble("price"));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
}
