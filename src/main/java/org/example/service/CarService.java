package org.example.service;

import org.example.dto.CarDTO;
import org.example.dto.CarModelDTO;
import org.example.dto.DealershipDTO;
import org.example.entity.CarEntity;
import org.example.mapper.CarMapper; // Убедитесь, что импортируете правильный класс

import java.util.Random;

public class CarService {

    private final Random random = new Random();
    private final CarMapper carMapper;

    // Конструктор для инициализации CarMapper
    public CarService(CarMapper carMapper) {
        this.carMapper = carMapper;
    }

    public CarDTO createCarWithRandomValues(int id, DealershipDTO dealership) {
        long startTime = System.nanoTime();

        String[] states = {"Не занят", "В пути", "В наличии", "Продан", "Забронирован"};
        String[] configurations = {"Базовая", "Специальная", "Спортивная"};
        String[] colors = {"Красный", "Синий", "Черный", "Белый", "Серый"};

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
        double price = 50000 + (random.nextDouble() * 50000);

        CarDTO car = new CarDTO(id, carModel, dealership, state, configuration, color, price);

        long endTime = System.nanoTime();
        System.out.println("Время выполнения createCarWithRandomValues: " + (endTime - startTime) + " нс");

        return car;
    }

    public void saveCar(CarDTO carDTO) {
        CarEntity carEntity = carMapper.carDTOToCarEntity(carDTO);
        System.out.println("Сохранен автомобиль: " + carEntity);
    }

    public void measurePerformanceForMultipleCreations(int numberOfCreations, DealershipDTO dealership) {
        long startTime = System.nanoTime();
        for (int i = 0; i < numberOfCreations; i++) {
            CarDTO car = createCarWithRandomValues(i, dealership);
            saveCar(car); // Сохраняем каждый созданный автомобиль
        }
        long endTime = System.nanoTime();
        System.out.println("Время выполнения measurePerformanceForMultipleCreations: " + (endTime - startTime) + " нс");
    }
}
