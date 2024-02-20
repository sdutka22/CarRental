package pl.edu.wszib.carRent.model;

public class Car {
    private String brand;
    private String model;
    private String plate;
    private int year;
    private String color;
    private boolean rent;

    public Car(String brand, String model, String plate, int year, String color, boolean rent) {
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.year = year;
        this.color = color;
        this.rent = rent;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-15s %-15s",
                brand, model, plate, year, color);
    }
}
