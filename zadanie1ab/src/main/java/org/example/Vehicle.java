package org.example;

public abstract class Vehicle {
    protected int id;
    protected String brand;
    protected String model;
    protected int year;
    protected double price;
    protected boolean rented;

    public Vehicle(int id, String brand, String model, int year, double price, boolean rented) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.rented = rented;
    }

    public abstract String toCSV();

    @Override
    public String toString() {
        return "ID: " + id + ", " + brand + " " + model + " (" + year + "), Cena: " + price + ", Wypożyczony: " + rented;
    }

    public int getId() {
        return id;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    @Override
    public abstract Vehicle clone(); //tu zmienione clone

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Vehicle vehicle = (Vehicle) obj;

        return id == vehicle.id;  //zad1ab
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }


}
