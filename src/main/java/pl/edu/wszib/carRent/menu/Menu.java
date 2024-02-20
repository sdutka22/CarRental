package pl.edu.wszib.carRent.menu;

import pl.edu.wszib.carRent.builder.CarBuilder;
import pl.edu.wszib.carRent.db.Cars;
import pl.edu.wszib.carRent.model.Car;
import pl.edu.wszib.carRent.db.initializer.UserFacade;
import pl.edu.wszib.carRent.model.User;

import java.util.Scanner;

public class Menu {
    private static final Cars cars = Cars.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserFacade userFacade = new UserFacade();
    private static User currentUser = null;
    private static int loginAttempts = 0;

    public void run() {
        int choice = -1;

        do {
            displayMenu();

            System.out.print("Wybierz opcję: ");
            String choiceStr = scanner.nextLine();

            try {
                choice = Integer.parseInt(choiceStr);

                if (currentUser == null && (choice < 1 || choice > 2)) {
                    System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
                    continue;
                }

                if (currentUser != null && (choice > 9)) {
                    System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        loginUser();
                        break;
                    case 2:
                        registerUser();
                        break;
                    case 3:
                        displayAllCars();
                        break;
                    case 4:
                        checkIfCarIsRented();
                        break;
                    case 5:
                        addNewCar();
                        break;
                    case 6:
                        removeCar();
                        break;
                    case 7:
                        rentCar();
                        break;
                    case 8:
                        returnCar();
                        break;
                    case 9:
                        logoutUser();
                        break;
                    case 0:
                        System.out.println("Koniec programu.");
                        break;
                    default:
                        System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
            }

        } while (choice != 0);

    }

    private void displayMenu() {
        System.out.println("Menu:");

        if (currentUser == null) {
            System.out.println("1. Zaloguj");
            System.out.println("2. Zarejestruj");
        } else {
            System.out.println("3. Wyświetl listę wszystkich aut");
            System.out.println("4. Sprawdź, czy auto jest wypożyczone");
            System.out.println("5. Dodaj nowe auto");
            System.out.println("6. Usuń auto");
            System.out.println("7. Wypożycz auto");
            System.out.println("8. Oddaj auto");
            System.out.println("9. Wyloguj");
            System.out.println("0. Wyjdź");
        }
    }

    private void displayAllCars() {
        System.out.println("Lista wszystkich aut:");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n",
                "Brand", "Model", "Plate", "Year", "Color");
        cars.getAllCars().forEach(System.out::println);
    }

    private void checkIfCarIsRented() {
        System.out.print("Podaj numer rejestracyjny auta: ");
        String plate = scanner.nextLine();
        boolean isRented = cars.isCarRented(plate);
        if (isRented) {
            System.out.println("Auto o numerze rejestracyjnym " + plate + " jest wypożyczone.");
        } else {
            System.out.println("Auto o numerze rejestracyjnym " + plate + " nie jest wypożyczone.");
        }
    }

    private void addNewCar() {
        System.out.print("Podaj markę auta: ");
        String brand = scanner.nextLine();
        System.out.print("Podaj model auta: ");
        String model = scanner.nextLine();
        System.out.print("Podaj numer rejestracyjny auta: ");
        String plate = scanner.nextLine();
        System.out.print("Podaj rok produkcji auta: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Podaj kolor auta: ");
        String color = scanner.nextLine();
        System.out.print("Czy auto jest wypożyczone? (true/false): ");
        boolean rent = scanner.nextBoolean();
        scanner.nextLine();

        Car newCar = new CarBuilder()
                .brand(brand)
                .model(model)
                .plate(plate)
                .year(year)
                .color(color)
                .rent(rent)
                .build();

        cars.addCar(newCar);
        System.out.println("Nowe auto dodane do listy.");
    }

    private void removeCar() {
        System.out.print("Podaj numer rejestracyjny auta do usunięcia: ");
        String plate = scanner.nextLine();
        cars.removeCar(plate);
    }

    private void registerUser() {
        System.out.print("Podaj nazwę użytkownika: ");
        String username = scanner.nextLine();
        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();
        userFacade.registerUser(username, password);
        System.out.println("Użytkownik zarejestrowany.");
    }

    private void loginUser() {
        if (currentUser != null) {
            System.out.println("Jesteś już zalogowany jako: " + currentUser.getLogin());
            return;
        }

        System.out.print("Podaj nazwę użytkownika: ");
        String username = scanner.nextLine();
        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();

        loginAttempts++;

        boolean loginSuccessful = userFacade.loginUser(username, password);
        if (loginSuccessful) {
            currentUser = new User(username, password);
            System.out.println("Logowanie udane. Witaj, " + currentUser.getLogin() + "!");
            loginAttempts = 0;
        } else {
            System.out.println("Logowanie nieudane.");

            if (loginAttempts >= 3) {
                System.out.println("Przekroczono limit prób logowania. Program zakończył działanie.");
                System.exit(0);
            }
        }
    }

    private void logoutUser() {
        if (currentUser != null) {
            System.out.println("Wylogowano użytkownika: " + currentUser.getLogin());
            currentUser = null;
        } else {
            System.out.println("Nie jesteś zalogowany.");
        }
    }

    private void rentCar() {
        if (currentUser == null) {
            System.out.println("Musisz być zalogowany, aby wypożyczyć samochód.");
            return;
        }

        System.out.print("Podaj numer rejestracyjny auta do wypożyczenia: ");
        String plate = scanner.nextLine();
        cars.rentCar(plate);
    }

    private void returnCar() {
        if (currentUser == null) {
            System.out.println("Musisz być zalogowany, aby zwrócić samochód.");
            return;
        }

        System.out.print("Podaj numer rejestracyjny auta do zwrotu: ");
        String plate = scanner.nextLine();
        cars.returnCar(plate);
    }

}
