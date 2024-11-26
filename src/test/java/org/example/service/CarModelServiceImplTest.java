package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CarModelServiceImplTest {

    private CarModelServiceImpl carModelService;
    private String csvFilePath = "src/test/resources/cars_test.csv";

    @BeforeEach
    void setUp() throws IOException {
        createTestCsvFile();
        carModelService = new CarModelServiceImpl();
        carModelService.load(csvFilePath);
    }

    private void createTestCsvFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            writer.write("id,brand,model,countryOrigin,countryCode\n");
            writer.write("1,Toyota,Corolla,Japan,JP\n");
            writer.write("2,Ford,Focus,USA,US\n");
            writer.write("3,Honda,Civic,Japan,JP\n");
            writer.write("4,Audi,A4,Germany,DE\n");
            writer.write("5,Toyota,Camry,Japan,JP\n");
        }
    }

    @Test
    void testGetUniqueBrands() {
        Set<String> uniqueBrands = carModelService.getUniqueBrands();

        assertEquals(4, uniqueBrands.size());
        assertTrue(uniqueBrands.contains("Toyota"));
        assertTrue(uniqueBrands.contains("Ford"));
        assertTrue(uniqueBrands.contains("Honda"));
        assertTrue(uniqueBrands.contains("Audi"));
    }

    @Test
    void testFindModelsByBrand() {
        List<String> toyotaModels = carModelService.findModelsByBrand("Toyota");

        assertEquals(2, toyotaModels.size());
        assertTrue(toyotaModels.contains("Corolla"));
        assertTrue(toyotaModels.contains("Camry"));

        List<String> hondaModels = carModelService.findModelsByBrand("Honda");
        assertEquals(1, hondaModels.size());
        assertTrue(hondaModels.contains("Civic"));

        List<String> unknownModels = carModelService.findModelsByBrand("Unknown");
        assertTrue(unknownModels.isEmpty());
    }

    @Test
    void testGroupByBrand() {
        Map<String, Integer> brandCount = carModelService.groupByBrand();

        assertEquals(4, brandCount.size());
        assertEquals(2, brandCount.get("Toyota"));
        assertEquals(1, brandCount.get("Ford"));
        assertEquals(1, brandCount.get("Honda"));
        assertEquals(1, brandCount.get("Audi"));
    }

    @Test
    void cleanUp() throws IOException {
        Files.deleteIfExists(Paths.get(csvFilePath));
    }
}