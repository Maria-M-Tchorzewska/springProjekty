package org.example;

import java.util.List;

public interface IVehicleRepository {
    void rentVehicle(int vehicleId);
    void returnVehicle(int vehicleId);
    List<Vehicle> getVehicles();
    void save();
    void load();

    void addVehicle(Vehicle vehicle);

    boolean removeVehicle(int vehicleId);
}