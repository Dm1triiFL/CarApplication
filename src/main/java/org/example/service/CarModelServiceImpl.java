package org.example.service;

import com.opencsv.exceptions.CsvValidationException;
import org.example.dto.CarModelDTO;
import org.example.entity.CarModelEntity;
import org.example.mapper.CarModelMapper; // Убедитесь, что вы импортируете правильный класс
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CarModelServiceImpl implements CarModelService {
    private List<CarModelDTO> carModelList = new ArrayList<>();
    private final CarModelMapper carModelMapper = CarModelMapper.INSTANCE; // Правильное именование

    @Override
    public void load(String fileName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] values;

            // Считывание заголовков
            csvReader.readNext();

            while ((values = csvReader.readNext()) != null) {
                if (values.length < 5) {
                    System.err.println("Недостаточно данных в строке: " + String.join(", ", values));
                    continue;
                }

                try {
                    long id = Long.parseLong(values[0]);
                    String brand = values[1];
                    String model = values[2];
                    String countryOrigin = values[3];
                    String countryCode = values[4];

                    CarModelDTO carModel = new CarModelDTO(id, brand, model, countryOrigin, countryCode);
                    carModelList.add(carModel);
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка формата числа в строке: " + String.join(", ", values));
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CarModelDTO> getAllCarDTOs(String brand) {
        return carModelList.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarModelDTO> findCarById(CarModelDTO car) {
        CarModelDTO searchCar = new CarModelDTO(car.getId(), null, null, null, null);
        return carModelList.stream()
                .filter(c -> c.equals(searchCar))
                .findFirst();
    }

    @Override
    public Map<String, Integer> getCarModelGroupByModel(String brand) {
        Map<String, Integer> modelCountMap = new HashMap<>();
        carModelList.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .forEach(car -> modelCountMap.put(car.getModel(), modelCountMap.getOrDefault(car.getModel(), 0) + 1));
        return modelCountMap;
    }

    @Override
    public Set<String> getUniqueBrands() {
        return carModelList.stream()
                .map(CarModelDTO::getBrand)
                .collect(Collectors.toSet());
    }

    @Override
    public List<String> findModelsByBrand(String brand) {
        return carModelList.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .map(CarModelDTO::getModel)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> groupByBrand() {
        Map<String, Integer> brandCountMap = new HashMap<>();
        carModelList.forEach(car -> brandCountMap.put(car.getBrand(), brandCountMap.getOrDefault(car.getBrand(), 0) + 1));
        return brandCountMap;
    }

    public CarModelEntity convertToEntity(CarModelDTO carModelDTO) {
        return carModelMapper.toEntity(carModelDTO);
    }

    public CarModelDTO convertToDTO(CarModelEntity carModelEntity) {
        return carModelMapper.toDTO(carModelEntity);
    }

    public List<CarModelDTO> convertToDTOs(List<CarModelEntity> carModelEntities) {
        return carModelEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CarModelEntity> convertToEntities(List<CarModelDTO> carModelDTOs) {
        return carModelDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}
