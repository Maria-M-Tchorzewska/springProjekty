package org.example;

public class Motorcycle extends Vehicle {
    private String category;

    public Motorcycle(int id, String brand, String model, int year, double price, boolean rented, String category) {
        super(id, brand, model, year, price, rented);
        this.category = category;
    }

    @Override
    public String toCSV() {
        return "Motorcycle;" + id + ";" + brand + ";" + model + ";" + year + ";" + price + ";" + rented + ";" + category;
    }

    @Override
    public String toString() {
        return super.toString() + ", Kategoria: " + category;
    }

    @Override
    public Vehicle clone() {
        return new Motorcycle(id, brand, model, year, price, rented, category);
    } //implementacja metody clone
}
