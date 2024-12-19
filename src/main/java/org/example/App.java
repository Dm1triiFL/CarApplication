package org.example;

import org.example.database.DatabaseSetup;
import org.example.dto.CarDTO;
import org.example.dto.DealershipDTO;
import org.example.entity.CarEntity;
import org.example.entity.DealershipEntity;
import org.example.repository.CarEntityRepository;
import org.example.repository.DealershipRepository;
import org.example.service.CarService;
import org.example.service.DealershipService;

import java.util.List;

public class App {
    public static void main(String[] args) {

        DatabaseSetup.setupDatabase();

//        DealershipRepository dealershipRepository = new DealershipRepository();
//        CarEntityRepository carEntityRepository = new CarEntityRepository();
//        CarService carService = new CarService(org.example.mapper.CarMapper.INSTANCE);
//        DealershipService dealershipService = new DealershipService(dealershipRepository, org.example.mapper.CarMapper.INSTANCE);
//
//        // Создаем новый автосалон и сохраняем его в базе данных
//        DealershipDTO dealership = new DealershipDTO("Автосалон 1");
//        dealershipRepository.create(new DealershipEntity(dealership.getName()));
//
//        // Создание автомобиля со случайными значениями
//        CarDTO car = carService.createCarWithRandomValues(1, dealership);
//        carService.saveCar(car); // Сохраняем автомобиль
//
//        // Получение всех автомобилей из базы данных
//        List<CarEntity> cars = carEntityRepository.findAll();
//        System.out.println("Все автомобили в базе данных:");
//        for (CarEntity c : cars) {
//            System.out.println(c);
//        }
//
//        // Обновление автомобиля
//        if (!cars.isEmpty()) {
//            CarEntity carToUpdate = cars.get(0);
//            carToUpdate.setState("На продаже"); // изменяем состояние автомобиля
//            carEntityRepository.update(carToUpdate);
//            System.out.println("Обновленный автомобиль: " + carToUpdate);
//        }
//
//        // Удаление автомобиля
//        if (!cars.isEmpty()) {
//            int carIdToDelete = cars.get(0).getId();
//            carEntityRepository.delete(carIdToDelete);
//            System.out.println("Автомобиль с ID " + carIdToDelete + " был удален.");
//        }
//
//        // Получение всех автосалонов
//        List<DealershipDTO> dealerships = dealershipService.getAllDealerships();
//        System.out.println("Все автосалоны в базе данных:");
//        for (DealershipDTO d : dealerships) {
//            System.out.println(d);
//        }
    }
}
