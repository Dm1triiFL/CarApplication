package org.example.entity;

import java.util.List;

public class DealerEntity {
    private String name;
    private List<CarEntity> cars;

    public DealerEntity() {
    }

    public DealerEntity(String name) {
        this.name = name;
    }

    // Геттеры и сеттеры

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CarEntity> getCars() {
        return cars;
    }

    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
    }
}
