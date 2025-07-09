package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private static final String FILE_NAME = "users.csv";
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        load();
    }

    @Override
    public User getUser(String login) {
        return users.stream()
                .filter(u -> u.getLogin().equalsIgnoreCase(login))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (User user : users) {
                writer.println(user.toCSV());
            }
            System.out.println("Użytkownicy zapisani do pliku.");
        } catch (IOException e) {
            System.out.println("Błąd zapisu użytkowników: " + e.getMessage());
        }
    }

    private void load() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Brak pliku, tworzenie domyślnych użytkowników...");
            generateDefaultUsers();
            return;
        }

        users.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 4) {
                    String login = data[0];
                    String hashedPassword = data[1];
                    String role = data[2];
                    User user = new User(login, hashedPassword, role, true);
                    if (!data[3].equals("brak")) {
                        int rentedId = Integer.parseInt(data[3]);
                        Vehicle rentedCar = VehicleRepository.getVehicleById(rentedId);
                        user.setRentedCar(rentedCar);
                    }
                    users.add(user);
                }
            }
            System.out.println("Użytkownicy wczytani z pliku.");
        } catch (IOException e) {
            System.out.println("Błąd odczytu użytkowników: " + e.getMessage());
        }
    }

    private void generateDefaultUsers() {
        users.add(new User("admin", "admin123", "ADMIN"));
        users.add(new User("john_doe", "pass1", "USER"));
        save();
    }
}
