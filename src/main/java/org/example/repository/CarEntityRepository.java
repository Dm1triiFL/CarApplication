package org.example.repository;

import org.example.entity.CarEntity;
import org.example.entity.CarModelEntity;
import org.example.entity.DealerEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarEntityRepository {
    private final Connection connection;

    public CarEntityRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(CarEntity car) {
        String sql = "INSERT INTO car (car_model_id, dealership_id, state, configuration, color, price) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, car.getCarModel().getId());
            statement.setLong(2, car.getDealership().getId());
            statement.setString(3, car.getState());
            statement.setString(4, car.getConfiguration());
            statement.setString(5, car.getColor());
            statement.setDouble(6, car.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CarEntity read(int id) {
        String sql = "SELECT * FROM car WHERE id = ?";
        CarEntity car = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                car = new CarEntity(
                        resultSet.getInt("id"),
                        new CarModelEntity(resultSet.getLong("car_model_id"), null, null, null, null), // Получите модель по ID
                        new DealerEntity(resultSet.getLong("dealership_id"), null, null), // Получите дилера по ID
                        resultSet.getString("state"),
                        resultSet.getString("configuration"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    public void update(CarEntity car) {
        String sql = "UPDATE car SET car_model_id = ?, dealership_id = ?, state = ?, configuration = ?, color = ?, price = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, car.getCarModel().getId());
            statement.setLong(2, car.getDealership().getId());
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

    public void delete(int id) {
        String sql = "DELETE FROM car WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CarEntity> findAll() {
        List<CarEntity> cars = new ArrayList<>();
        String sql = "SELECT * FROM car";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                CarEntity car = new CarEntity(
                        resultSet.getInt("id"),
                        new CarModelEntity(resultSet.getLong("car_model_id"), null, null, null, null), // Получите модель по ID
                        new DealerEntity(resultSet.getLong("dealership_id"), null, null), // Получите дилера по ID
                        resultSet.getString("state"),
                        resultSet.getString("configuration"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<CarEntity> findByCondition(String condition) {
        List<CarEntity> cars = new ArrayList<>();
        String sql = "SELECT * FROM car WHERE " + condition;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                CarEntity car = new CarEntity(
                        resultSet.getInt("id"),
                        new CarModelEntity(resultSet.getLong("car_model_id"), null, null, null, null), // Получите модель по ID
                        new DealerEntity(resultSet.getLong("dealership_id"), null, null), // Получите дилера по ID
                        resultSet.getString("state"),
                        resultSet.getString("configuration"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
}
