package org.example.repository;

import org.example.database.DatabaseConnection;
import org.example.entity.CarModelEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarModelRepository {

    public void insertCarModel(CarModelEntity carModel) {
        String sql = "INSERT INTO CarModel (brand, model, countryOrigin, countryCode) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, carModel.getBrand());
            statement.setString(2, carModel.getModel());
            statement.setString(3, carModel.getCountryOrigin());
            statement.setString(4, carModel.getCountryCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CarModelEntity getCarModel(long id) {
        String sql = "SELECT * FROM CarModel WHERE id = ?";
        CarModelEntity carModel = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                carModel = new CarModelEntity();
                carModel.setId(resultSet.getLong("id"));
                carModel.setBrand(resultSet.getString("brand"));
                carModel.setModel(resultSet.getString("model"));
                carModel.setCountryOrigin(resultSet.getString("countryOrigin"));
                carModel.setCountryCode(resultSet.getString("countryCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carModel;
    }

    public List<CarModelEntity> getAllCarModels() {
        String sql = "SELECT * FROM CarModel";
        List<CarModelEntity> carModels = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                CarModelEntity carModel = new CarModelEntity();
                carModel.setId(resultSet.getLong("id"));
                carModel.setBrand(resultSet.getString("brand"));
                carModel.setModel(resultSet.getString("model"));
                carModel.setCountryOrigin(resultSet.getString("countryOrigin"));
                carModel.setCountryCode(resultSet.getString("countryCode"));
                carModels.add(carModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carModels;
    }

    public void updateCarModel(CarModelEntity carModel) {
        String sql = "UPDATE CarModel SET brand = ?, model = ?, countryOrigin = ?, countryCode = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, carModel.getBrand());
            statement.setString(2, carModel.getModel());
            statement.setString(3, carModel.getCountryOrigin());
            statement.setString(4, carModel.getCountryCode());
            statement.setLong(5, carModel.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCarModel(long id) {
        String sql = "DELETE FROM CarModel WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
