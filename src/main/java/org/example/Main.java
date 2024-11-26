package org.example;

import org.example.dto.CarModelDTO;
import org.example.service.CarModelService;
import org.example.service.CarModelServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Создание экземпляра сервиса
        CarModelService carService = new CarModelServiceImpl();

        // Загрузка данных из CSV-файла
        String filePath = "C:\\Users\\dmshu\\IdeaProjects\\CarApplication\\src\\main\\resources\\cars.csv"; // Укажите путь к вашему CSV-файлу
        carService.load(filePath);

        // 1: Получение всех автомобилей определенной марки
        System.out.println("1: Получение всех автомобилей марки Toyota:");
        List<CarModelDTO> toyotaCars = carService.getAllCarDTOs("Toyota");
        if (toyotaCars.isEmpty()) {
            System.out.println("Нет автомобилей марки Toyota.");
        } else {
            toyotaCars.forEach(car -> System.out.println(car));
        }

        // 2: Поиск автомобиля по ID
        System.out.println("\n2: Поиск автомобиля по ID:");
        CarModelDTO searchById = new CarModelDTO(3L, null, null, null, null); // Ищем по ID = 3
        Optional<CarModelDTO> foundById = carService.findCarById(searchById);
        foundById.ifPresent(car -> System.out.println("Найденный автомобиль: " + car));

        // 3: Группировка автомобилей по моделям
        System.out.println("\n3: Группировка автомобилей марки BMW по моделям:");
        Map<String, Integer> modelCountMap = carService.getCarModelGroupByModel("BMW");
        modelCountMap.forEach((model, count) ->
                System.out.println("Модель: " + model + ", Количество: " + count));

        // 4: Получение уникальных марок автомобилей
        System.out.println("\n4: Уникальные марки автомобилей:");
        Set<String> uniqueBrands = carService.getUniqueBrands();
        uniqueBrands.forEach(System.out::println);

        // 5: Поиск моделей по марке
        String brandToSearch = "Toyota";
        System.out.println("\n5: Модели автомобилей марки " + brandToSearch + ":");
        List<String> toyotaModels = carService.findModelsByBrand(brandToSearch);
        if (toyotaModels.isEmpty()) {
            System.out.println("Нет моделей для марки " + brandToSearch + ".");
        } else {
            toyotaModels.forEach(System.out::println);
        }

        // 6: Группировка по марке автомобилей
        System.out.println("\n6: Группировка автомобилей по марке:");
        Map<String, Integer> brandCountMap = carService.groupByBrand();
        brandCountMap.forEach((brand, count) ->
                System.out.println("Марка: " + brand + ", Количество: " + count));
    }
}