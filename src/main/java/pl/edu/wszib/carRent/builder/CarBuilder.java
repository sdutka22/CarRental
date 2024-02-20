package pl.edu.wszib.carRent.builder;

import pl.edu.wszib.carRent.model.Car;

public class CarBuilder {
    private String brand;
    private String model;
    private String plate;
    private int year;
    private String color;
    private boolean rent;

    public CarBuilder brand(String brand){
        this.brand = brand;
        return this;
    }

    public CarBuilder model(String model){
        this.model = model;
        return this;
    }

    public CarBuilder plate(String plate){
        this.plate = plate;
        return this;
    }

    public CarBuilder year(int year){
        this.year = year;
        return this;
    }

    public CarBuilder color(String color){
        this.color = color;
        return this;
    }

    public CarBuilder rent(Boolean rent){
        this.rent = rent;
        return this;
    }

    public Car build(){
        return new Car(brand, model, plate, year, color, rent);
    }
}
