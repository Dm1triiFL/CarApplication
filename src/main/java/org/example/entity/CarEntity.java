package org.example.entity;

public class CarEntity {
    private int id;
    private CarModelEntity carModel;
    private DealerEntity dealership;
    private String state;
    private String configuration;
    private String color;
    private double price;

    public CarEntity() {}

    public CarEntity(int id, CarModelEntity carModel, DealerEntity dealership, String state, String configuration, String color, double price) {
        this.id = id;
        this.carModel = carModel;
        this.dealership = dealership;
        this.state = state;
        this.configuration = configuration;
        this.color = color;
        this.price = price;
    }

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

    public DealerEntity getDealership() {
        return dealership;
    }

    public void setDealership(DealerEntity dealership) {
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
}
