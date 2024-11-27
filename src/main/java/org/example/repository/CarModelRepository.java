package org.example.repository;

import org.example.entity.CarModelEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarModelRepository {
    private final Connection connection;

    public CarModelRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(CarModelEntity carModel) {
        String sql = "INSERT INTO car_model (brand, model, country_origin, country_code) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, carModel.getBrand());
            statement.setString(2, carModel.getModel());
            statement.setString(3, carModel.getCountryOrigin());
            statement.setString(4, carModel.getCountryCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CarModelEntity read(long id) {
        String sql = "SELECT * FROM car_model WHERE id = ?";
        CarModelEntity carModel = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                carModel = new CarModelEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getString("country_origin"),
                        resultSet.getString("country_code")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carModel;
    }

    public void update(CarModelEntity carModel) {
        String sql = "UPDATE car_model SET brand = ?, model = ?, country_origin = ?, country_code = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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

    public void delete(long id) {
        String sql = "DELETE FROM car_model WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CarModelEntity> findAll() {
        List<CarModelEntity> carModels = new ArrayList<>();
        String sql = "SELECT * FROM car_model";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                CarModelEntity carModel = new CarModelEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getString("country_origin"),
                        resultSet.getString("country_code")
                );
                carModels.add(carModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carModels;
    }

    public List<CarModelEntity> findByCondition(String condition) {
        List<CarModelEntity> carModels = new ArrayList<>();
        String sql = "SELECT * FROM car_model WHERE " + condition;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                CarModelEntity carModel = new CarModelEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getString("country_origin"),
                        resultSet.getString("country_code")
                );
                carModels.add(carModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carModels;
    }
}
