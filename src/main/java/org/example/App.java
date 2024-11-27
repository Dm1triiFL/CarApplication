package org.example;

import org.example.entity.CarEntity;
import org.example.entity.CarModelEntity;
import org.example.entity.DealerEntity;
import org.example.repository.CarEntityRepository;
import org.example.repository.CarModelRepository;
import org.example.repository.DealerRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    private static final String URL = "jdbc:h2:file:C:/Users/dmshu/car"; // Укажите правильный путь
    private static final String USER = "sa";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = connection.createStatement()) {

            // Создание таблиц, если они не существуют
            String createDealerTable = "CREATE TABLE IF NOT EXISTS dealership (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "address VARCHAR(255))";

            String createCarModelTable = "CREATE TABLE IF NOT EXISTS car_model (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "brand VARCHAR(255), " +
                    "model VARCHAR(255), " +
                    "country_origin VARCHAR(255), " +
                    "country_code VARCHAR(255))";

            String createCarTable = "CREATE TABLE IF NOT EXISTS car (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "car_model_id BIGINT, " +
                    "dealership_id BIGINT, " +
                    "state VARCHAR(255), " +
                    "configuration VARCHAR(255), " +
                    "color VARCHAR(255), " +
                    "price DOUBLE, " +
                    "FOREIGN KEY (car_model_id) REFERENCES car_model(id), " +
                    "FOREIGN KEY (dealership_id) REFERENCES dealership(id))";

            stmt.execute(createDealerTable);
            stmt.execute(createCarModelTable);
            stmt.execute(createCarTable);

            // Создание объектов репозиториев
            CarModelRepository carModelRepo = new CarModelRepository(connection);
            DealerRepository dealerRepo = new DealerRepository(connection);
            CarEntityRepository carRepo = new CarEntityRepository(connection);

            // CRUD для DealerEntity
            DealerEntity dealer = new DealerEntity(0, "Дилер 1", "ул. Ленина, 12");
            dealerRepo.create(dealer);
            System.out.println("Создан дилер: " + dealer.getName());

            // Создание CarModelEntity
            CarModelEntity carModel = new CarModelEntity(0, "Toyota", "Camry", "Japan", "JP");
            carModelRepo.create(carModel);
            System.out.println("Создана модель автомобиля: " + carModel.getModel());

            // Теперь создаем CarEntity с использованием ID созданной модели и дилера
            CarEntity car = new CarEntity(0, carModel, dealer, "new", "premium", "black", 30000);
            carRepo.create(car);
            System.out.println("Создан автомобиль: " + car.getCarModel().getModel());

            // Обновление информации о дилере
            dealer.setName("Обновленный Дилер 1");
            dealerRepo.update(dealer);
            System.out.println("Обновлен дилер: " + dealer.getName());

            DealerEntity retrievedDealer = dealerRepo.read(dealer.getId());
            if (retrievedDealer != null) {
                System.out.println("Получен дилер: " + retrievedDealer.getName());
            } else {
                System.out.println("Дилер не найден.");
            }

            dealerRepo.delete(dealer.getId());
            System.out.println("Удален дилер с ID: " + dealer.getId());

            // Обновляем цвет автомобиля
            car.setColor("Обновленный цвет");
            carRepo.update(car);
            System.out.println("Обновлённый цвет автомобиля: " + car.getColor());

            CarEntity retrievedCar = carRepo.read(car.getId());
            if (retrievedCar != null) {
                System.out.println("Получен автомобиль: " + retrievedCar.getCarModel().getModel());
            } else {
                System.out.println("Автомобиль не найден.");
            }

            carRepo.delete(car.getId());
            System.out.println("Удален автомобиль с ID: " + car.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
