package org.example.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void setupDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Создание таблиц
            stmt.execute("CREATE TABLE IF NOT EXISTS CarModel (" +
                    "id INT PRIMARY KEY, " +
                    "brand VARCHAR(255) NOT NULL, " +
                    "model VARCHAR(255) NOT NULL, " +
                    "countryOrigin VARCHAR(255), " +
                    "countryCode VARCHAR(10));");

            stmt.execute("CREATE TABLE IF NOT EXISTS Dealership (" +
                    "name VARCHAR(255) PRIMARY KEY);");

            stmt.execute("CREATE TABLE IF NOT EXISTS Car (" +
                    "id INT PRIMARY KEY, " +
                    "carModelId INT, " +
                    "dealershipName VARCHAR(255), " +
                    "state VARCHAR(50), " +
                    "configuration VARCHAR(255), " +
                    "color VARCHAR(50), " +
                    "price DOUBLE, " +
                    "FOREIGN KEY (carModelId) REFERENCES CarModel(id), " +
                    "FOREIGN KEY (dealershipName) REFERENCES Dealership(name));");

            // Вставка данных
            stmt.execute("INSERT INTO CarModel (id, brand, model, countryOrigin, countryCode) VALUES " +
                    "(1, 'Toyota', 'Camry', 'Japan', 'JP'), " +
                    "(2, 'Volkswagen', 'Golf', 'Germany', 'DE'), " +
                    "(3, 'Ford', 'Mustang', 'USA', 'US') ON CONFLICT (id) DO NOTHING;");

            stmt.execute("INSERT INTO Dealership (name) VALUES " +
                    "('Super Cars'), " +
                    "('Elite Motors'), " +
                    "('Luxury Autos') ON CONFLICT (name) DO NOTHING;");

            stmt.execute("INSERT INTO Car (id, carModelId, dealershipName, state, configuration, color, price) VALUES " +
                    "(1, 1, 'Super Cars', 'New', 'Standard', 'Black', 30000), " +
                    "(2, 2, 'Elite Motors', 'Used', 'Premium', 'Red', 25000), " +
                    "(3, 3, 'Luxury Autos', 'New', 'Standard', 'Blue', 45000) ON CONFLICT (id) DO NOTHING;");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
