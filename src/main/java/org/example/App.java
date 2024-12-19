package org.example;

import org.example.database.DatabaseSetup;
import org.example.dto.CarDTO;
import org.example.dto.DealershipDTO;
import org.example.entity.CarEntity;
import org.example.entity.CarModelEntity;
import org.example.entity.DealershipEntity;
import org.example.repository.CarModelRepository;
import org.example.repository.CarRepository;
import org.example.repository.DealershipRepository;
import org.example.service.CarService;
import org.example.service.DealershipService;

import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {

        DatabaseSetup.setupDatabase();

        CarModelRepository carModelRepository = new CarModelRepository();
        CarRepository carRepository = new CarRepository();
        DealershipRepository dealershipRepository = new DealershipRepository();

        // Пример создания новых моделей автомобилей
        CarModelEntity toyotaModel = new CarModelEntity(1, "Toyota", "Camry", "Japan", "JP");
        carModelRepository.create(toyotaModel);
        System.out.println("Создана новая модель: " + toyotaModel);

        // Пример создания новых дилерских центров
        DealershipEntity dealership = new DealershipEntity("GRAND_AUTO");
        dealershipRepository.create(dealership);
        System.out.println("Создан дилерский центр: " + dealership);

        // Пример создания нового автомобиля
        CarEntity newCar = new CarEntity(1, 1, "GRAND_AUTO", "new", "Sedan", "Red", 20000.00);
        carRepository.create(newCar);
        System.out.println("Создан новый автомобиль: " + newCar);

        // Пример получения всех автомобилей
        List<CarEntity> cars = carRepository.findAll();
        System.out.println("\nВсе автомобили в базе данных:");
        cars.forEach(car -> System.out.println(car));

        // Пример получения всех моделей автомобилей
        List<CarModelEntity> carModels = carModelRepository.findAll();
        System.out.println("\nВсе модели автомобилей в базе данных:");
        carModels.forEach(model -> System.out.println(model));

        // Пример поиска автомобиля по ID
        int searchCarId = 1; // ID для поиска
        Optional<CarEntity> foundCar = carRepository.findById(searchCarId);
        foundCar.ifPresent(car -> System.out.println("\nНайденный автомобиль: " + car));

        // Пример обновления автомобиля
        if (foundCar.isPresent()) {
            CarEntity carToUpdate = foundCar.get();
            carToUpdate.setColor("Blue"); // Изменяем цвет автомобиля
            carToUpdate.setPrice(21000.00); // Обновляем цену
            carRepository.update(carToUpdate);
            System.out.println("\nАвтомобиль обновлён: " + carToUpdate);
        }

        // Пример удаления автомобиля по ID
        int deleteCarId = 1; // ID для удаления
        carRepository.delete(deleteCarId);
        System.out.println("\nАвтомобиль с ID " + deleteCarId + " удалён.");

        // Проверка, что автомобиль был удалён
        Optional<CarEntity> checkDeletedCar = carRepository.findById(deleteCarId);
        if (checkDeletedCar.isEmpty()) {
            System.out.println("Автомобиль с ID " + deleteCarId + " не найден в базе данных.");
        }

        // Пример удаления дилерского центра
        String dealershipToDelete = "GRAND_AUTO"; // Имя для удаления
        dealershipRepository.delete(dealershipToDelete);
        System.out.println("\nДилерский центр " + dealershipToDelete + " удалён.");
    }
}
