package org.example.service;

import com.opencsv.exceptions.CsvValidationException;
import org.example.dto.CarModelDTO;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CarModelServiceImpl implements CarModelService {
    private List<CarModelDTO> carModelList = new ArrayList<>();

    @Override
    public void load(String fileName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] values;

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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
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
        if (carModelList.contains(searchCar)) {
            return carModelList.stream()
                    .filter(c -> c.equals(car))
                    .findFirst();
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Map<String, Integer> getCarModelGroupByModel(String brand) {
        Map<String, Integer> modelCountMap = new HashMap<>();
        for (CarModelDTO car : carModelList) {
            if (car.getBrand().equalsIgnoreCase(brand)) {
                modelCountMap.put(car.getModel(), modelCountMap.getOrDefault(car.getModel(), 0) + 1);
            }
        }
        return modelCountMap;
    }

    @Override
    public Set<String> getUniqueBrands() {
        Set<String> uniqueBrands = new HashSet<>();
        for (CarModelDTO car : carModelList) {
            uniqueBrands.add(car.getBrand());
        }
        return uniqueBrands;
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
        for (CarModelDTO car : carModelList) {
            brandCountMap.put(car.getBrand(), brandCountMap.getOrDefault(car.getBrand(), 0) + 1);
        }
        return brandCountMap;
    }
}