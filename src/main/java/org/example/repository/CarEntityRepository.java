package org.example.repository;

import org.example.database.DatabaseConnection;
import org.example.entity.CarEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarEntityRepository {

    public void insertCar(CarEntity car) {
        String sql = "INSERT INTO Car (carModelId, dealershipName, state, configuration, color, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, car.getCarModelId());
            statement.setString(2, car.getDealershipName());
            statement.setString(3, car.getState());
            statement.setString(4, car.getConfiguration());
            statement.setString(5, car.getColor());
            statement.setDouble(6, car.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CarEntity getCar(int id) {
        String sql = "SELECT * FROM Car WHERE id = ?";
        CarEntity car = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                car = new CarEntity();
                car.setId(resultSet.getInt("id"));
                car.setCarModelId(resultSet.getInt("carModelId"));
                car.setDealershipName(resultSet.getString("dealershipName"));
                car.setState(resultSet.getString("state"));
                car.setConfiguration(resultSet.getString("configuration"));
                car.setColor(resultSet.getString("color"));
                car.setPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    public List<CarEntity> getAllCars() {
        String sql = "SELECT * FROM Car";
        List<CarEntity> cars = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
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

    public void updateCar(CarEntity car) {
        String sql = "UPDATE Car SET carModelId = ?, dealershipName = ?, state = ?, configuration = ?, color = ?, price = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, car.getCarModelId());
            statement.setString(2, car.getDealershipName());
            statement.setString(3, car.getState());
            statement.setString(4, car.getConfiguration());
            statement.setString(5, car.getColor());
            statement.setDouble(6, car.getPrice());
            statement.setInt(7, car.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCar(int id) {
        String sql = "DELETE FROM Car WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
