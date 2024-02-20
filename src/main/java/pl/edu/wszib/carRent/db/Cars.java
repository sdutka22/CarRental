package pl.edu.wszib.carRent.db;

import pl.edu.wszib.carRent.builder.CarBuilder;
import pl.edu.wszib.carRent.model.Car;

import java.util.ArrayList;
import java.util.List;

public class Cars {
    private List<Car> carsList;
    private static Cars INSTANCE = new Cars();

    private Cars() {
        this.carsList = new ArrayList<>();
        carsList.add(new CarBuilder().brand("Toyota").model("Camry").plate("XYZ123").year(2020).color("Blue").rent(false).build());
        carsList.add(new CarBuilder().brand("Ford").model("Focus").plate("ABC456").year(2019).color("Red").rent(true).build());
        carsList.add(new CarBuilder().brand("Honda").model("Civic").plate("DEF789").year(2021).color("Silver").rent(false).build());
        carsList.add(new CarBuilder().brand("Chevrolet").model("Malibu").plate("GHI012").year(2022).color("White").rent(true).build());
        carsList.add(new CarBuilder().brand("Volkswagen").model("Golf").plate("JKL345").year(2020).color("Black").rent(false).build());
    }

    public List<Car> getAllCars() {
        return carsList;
    }

    public boolean isCarRented(String plate) {
        for (Car car : carsList) {
            if (car.getPlate().equals(plate)) {
                return car.isRent();
            }
        }
        return false;
    }

    public void rentCar(String plate) {
        for (Car car : carsList) {
            if (car.getPlate().equals(plate) && !car.isRent()) {
                car.setRent(true);
                System.out.println("Samochód o numerze rejestracyjnym " + plate + " został wypożyczony.");
                return;
            }
        }
        System.out.println("Nie można wypożyczyć samochodu o numerze rejestracyjnym " + plate + ".");
    }

    public void returnCar(String plate) {
        for (Car car : carsList) {
            if (car.getPlate().equals(plate) && car.isRent()) {
                car.setRent(false);
                System.out.println("Samochód o numerze rejestracyjnym " + plate + " został zwrócony.");
                return;
            }
        }
        System.out.println("Nie można zwrócić samochodu o numerze rejestracyjnym " + plate + ".");
    }

    public void addCar(Car car) {
        carsList.add(car);
    }

    public void removeCar(String plate) {
        for (Car car : carsList) {
            if (car.getPlate().equals(plate)) {
                if (!car.isRent()) {
                    carsList.remove(car);
                    System.out.println("Auto o numerze rejestracyjnym " + plate + " usunięte z listy.");
                } else {
                    System.out.println("Nie można usunąć wypożyczonego auta o numerze rejestracyjnym " + plate + ".");
                }
                return;
            }
        }
        System.out.println("Auto o numerze rejestracyjnym " + plate + " nie zostało znalezione.");
    }


    public static Cars getInstance() {
        return INSTANCE;
    }
}
