package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne // Связь с CarModelEntity
    private CarModelEntity carModel;

    @ManyToOne // Связь с DealershipEntity
    private DealershipEntity dealership;

    private String state;
    private String configuration;
    private String color;
    private double price;

    public CarEntity() {
    }

    public CarEntity(CarModelEntity carModel, DealershipEntity dealership, String state, String configuration, String color, double price) {
        this.carModel = carModel;
        this.dealership = dealership;
        this.state = state;
        this.configuration = configuration;
        this.color = color;
        this.price = price;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarModelEntity getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModelEntity carModel) {
        this.carModel = carModel;
    }

    public DealershipEntity getDealership() {
        return dealership;
    }

    public void setDealership(DealershipEntity dealership) {
        this.dealership = dealership;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCarModelId(int carModelId) {
        this.id = carModelId;
    }

    public void setDealershipName(DealershipEntity dealershipName) {
        this.dealership = dealershipName;
    }

}
