package org.example.service;

import org.example.dto.CarDTO;
import org.example.dto.CarModelDTO;
import org.example.dto.DealershipDTO;
import org.example.entity.CarEntity;
import org.example.mapper.CarMapper; // Убедитесь, что импортируете правильный класс
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CarService {
    private Random random = new Random();
    private final CarMapper carMapper = CarMapper.INSTANCE; // Исправлено на правильный тип и имя переменной

    public CarDTO createCarWithRandomValues(int id, DealershipDTO dealership) {
        // Измерение времени
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

        // Время окончания
        long endTime = System.nanoTime();
        System.out.println("Время выполнения createCarWithRandomValues: " + (endTime - startTime) + " нс");

        return car;
    }

    public void measurePerformanceForMultipleCreations(int numberOfCreations, DealershipDTO dealership) {
        long startTime = System.nanoTime();
        for (int i = 0; i < numberOfCreations; i++) {
            createCarWithRandomValues(i, dealership);
        }
        long endTime = System.nanoTime();
        System.out.println("Время выполнения для " + numberOfCreations + " созданий: " + (endTime - startTime) + " нс");
    }

    public CarEntity convertToEntity(CarDTO carDTO) {
        return carMapper.toEntity(carDTO);
    }

    public CarDTO convertToDTO(CarEntity carEntity) {
        return carMapper.toDTO(carEntity);
    }

    public List<CarEntity> convertToEntities(List<CarDTO> carDTOs) {
        return carDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public List<CarDTO> convertToDTOs(List<CarEntity> carEntities) {
        return carEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
