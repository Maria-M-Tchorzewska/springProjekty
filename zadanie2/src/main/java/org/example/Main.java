package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final IVehicleRepository vehicleRepository = new VehicleRepository();
    private static final UserRepository userRepository = new UserRepository();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Wypożyczalnia Pojazdów ---");

        while (true) {
            System.out.println("\n1. Zaloguj się");
            System.out.println("2. Utwórz konto");
            System.out.println("3. Zakończ program");
            System.out.print("Wybierz opcję: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Login: ");
                String login = scanner.nextLine();
                System.out.print("Hasło: ");
                String password = scanner.nextLine();

                User user = userRepository.getUser(login);
                if (user != null && user.getPassword().equals(PasswordHasher.hashPassword(password))) {
                    System.out.println("Zalogowano jako " + user.getRole());
                    if (user instanceof Admin) {
                        adminMenu((Admin) user, scanner);
                    } else {
                        userMenu(user, scanner);
                    }
                } else {
                    System.out.println("Nieprawidłowe dane logowania.");
                }

            } else if (option == 2) {
                System.out.print("Nowy login: ");
                String login = scanner.nextLine();
                System.out.print("Nowe hasło: ");
                String password = scanner.nextLine();
                System.out.print("Rola (ADMIN/USER): ");
                String role = scanner.nextLine().toUpperCase();

                User newUser = role.equals("ADMIN")
                        ? new Admin(login, password)
                        : new User(login, password, role);
                userRepository.getUsers().add(newUser);
                userRepository.save();
                System.out.println("Utworzono nowe konto.");
            } else if (option == 3) {
                System.out.println("Zamykanie programu...");
                break;
            } else {
                System.out.println("Niepoprawny wybór.");
            }
        }

        scanner.close();
    }

    private static void adminMenu(Admin admin, Scanner scanner) {
        while (true) {
            System.out.println("\n--- MENU ADMINA ---");
            System.out.println("1. Dodaj pojazd");
            System.out.println("2. Usuń pojazd");
            System.out.println("3. Wyświetl pojazdy");
            System.out.println("4. Wyświetl użytkowników");
            System.out.println("5. Wyloguj się");
            System.out.print("Wybierz opcję: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("Marka: ");
                    String brand = scanner.nextLine();
                    System.out.print("Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Rok: ");
                    int year = scanner.nextInt();
                    System.out.print("Cena: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Typ (Car/Motorcycle): ");
                    String type = scanner.nextLine();

                    if (type.equalsIgnoreCase("Car")) {
                        admin.addVehicle(vehicleRepository, new Car(brand, model, year, price));
                    } else if (type.equalsIgnoreCase("Motorcycle")) {
                        System.out.print("Kategoria: ");
                        String category = scanner.nextLine();
                        admin.addVehicle(vehicleRepository, new Motorcycle(brand, model, year, price, category));
                    } else {
                        System.out.println("Niepoprawny typ.");
                    }
                }
                case 2 -> {
                    System.out.print("ID pojazdu do usunięcia: ");
                    int id = scanner.nextInt();
                    admin.removeVehicle(vehicleRepository, id);
                }
                case 3 -> {
                    admin.listVehicles(vehicleRepository);
                    admin.listUsers(userRepository);
                }
                case 5 -> {
                    System.out.println("Wylogowano z konta admina.");
                    return;
                }
                default -> System.out.println("Niepoprawna opcja.");
            }
        }
    }

    private static void userMenu(User user, Scanner scanner) {
        while (true) {
            System.out.println("\n--- MENU UŻYTKOWNIKA ---");
            System.out.println("1. Wyświetl dostępne pojazdy");
            System.out.println("2. Wypożycz pojazd");
            System.out.println("3. Zwróć pojazd");
            System.out.println("4. Moje dane");
            System.out.println("5. Wyloguj się");
            System.out.print("Wybierz opcję: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    List<Vehicle> vehicles = vehicleRepository.getVehicles();
                    for (Vehicle v : vehicles) {
                        System.out.println(v);
                    }
                }
                case 2 -> {
                    System.out.print("ID pojazdu do wypożyczenia: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Vehicle vehicle = VehicleRepository.getVehicleById(id);
                    if (vehicle != null && !vehicle.isRented()) {
                        user.rentCar(vehicle);
                        vehicleRepository.save();
                        userRepository.save();
                    } else {
                        System.out.println("Pojazd niedostępny.");
                    }
                }
                case 3 -> {
                    if (user.getRentedCar() != null) {
                        user.returnCar();
                        vehicleRepository.save();
                        userRepository.save();
                    } else {
                        System.out.println("Nie masz wypożyczonego pojazdu.");
                    }
                }
                case 4 -> System.out.println(user);
                case 5 -> {
                    System.out.println("Wylogowano.");
                    return;
                }
                default -> System.out.println("Niepoprawna opcja.");
            }
        }
    }
}
