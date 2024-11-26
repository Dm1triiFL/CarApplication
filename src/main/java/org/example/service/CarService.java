package org.example.service;

import org.example.dto.CarDTO;
import org.example.dto.CarModelDTO;
import org.example.dto.DealershipDTO;

import java.util.Random;

public class CarService {
    private Random random = new Random();

    public CarDTO createCarWithRandomValues(int id, DealershipDTO dealership) {
        String[] states = {"Не занят", "В пути", "В наличии", "Продан", "Забронирован"};
        String[] configurations = {"Базовая", "Специальная", "Спортивная"};
        String[] colors = {"Красный", "Синий", "Черный", "Белый", "Серый"};

        // Генерация случайных данных для модели автомобиля
        CarModelDTO carModel = new CarModelDTO(
                id,
                "Brand " + (random.nextInt(10) + 1),
                "Model " + (random.nextInt(10) + 1),
                "Country " + (random.nextInt(5) + 1),
                "Code " + (random.nextInt(5) + 1)
        );

        String state = states[random.nextInt(states.length)];
        String configuration = configurations[random.nextInt(configurations.length)];
        String color = colors[random.nextInt(colors.length)];
        double price = 20000 + (random.nextDouble() * 30000);

        return new CarDTO(id, carModel, dealership, state, configuration, color, price);
    }
}


