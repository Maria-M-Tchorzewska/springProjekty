package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements IVehicleRepository {
    private List<Vehicle> vehicles = new ArrayList<>();
    private final String filePath = "src/vehicles.csv";

    public VehicleRepository() {
        load();
    }

    @Override
    public void rentVehicle(int id) {
        for (Vehicle v : vehicles) {
            if (v.getId() == id) {
                if (!v.isRented()) {
                    v.setRented(true);
                    System.out.println("Pojazd wypożyczony.");
                } else {
                    System.out.println("Pojazd już wypożyczony.");
                }
                save();
                return;
            }
        }
        System.out.println("Nie znaleziono pojazdu o podanym ID.");
    }

    @Override
    public void returnVehicle(int id) {
        for (Vehicle v : vehicles) {
            if (v.getId() == id) {
                if (v.isRented()) {
                    v.setRented(false);
                    System.out.println("Pojazd zwrócony.");
                } else {
                    System.out.println("Pojazd nie był wypożyczony.");
                }
                save();
                return;
            }
        }
        System.out.println("Nie znaleziono pojazdu o podanym ID.");
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Vehicle v : vehicles) {
                writer.write(v.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Błąd zapisu do pliku: " + e.getMessage());
        }
    }

    @Override
    public void load() {
        vehicles.clear();
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String type = parts[0];
                int id = Integer.parseInt(parts[1]);
                String brand = parts[2];
                String model = parts[3];
                int year = Integer.parseInt(parts[4]);
                double price = Double.parseDouble(parts[5]);
                boolean rented = Boolean.parseBoolean(parts[6]);

                if ("Car".equals(type)) {
                    vehicles.add(new Car(id, brand, model, year, price, rented));
                } else if ("Motorcycle".equals(type)) {
                    String category = parts[7];
                    vehicles.add(new Motorcycle(id, brand, model, year, price, rented, category));
                }
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu pliku: " + e.getMessage());
        }
    }
}
