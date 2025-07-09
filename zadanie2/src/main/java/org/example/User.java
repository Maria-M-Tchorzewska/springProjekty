package org.example;

public class User {
    private String login;
    private String password;
    private String role;
    private Vehicle rentedCar;

    public User(String login, String password, String role) {
        this.login = login;
        this.password = PasswordHasher.hashPassword(password);
        this.role = role;
    }

    public User(String login, String hashedPassword, String role, boolean alreadyHashed) {
        this.login = login;
        this.password = hashedPassword;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Vehicle getRentedCar() {
        return rentedCar;
    }

    public void rentCar(Vehicle car) {
        if (this.rentedCar == null) {
            this.rentedCar = car;
            car.setRented(true);
            System.out.println("Użytkownik " + login + " wypożyczył pojazd: " + car);
        } else {
            System.out.println("Masz już wypożyczony pojazd.");
        }
    }

    public void returnCar() {
        if (this.rentedCar != null) {
            this.rentedCar.setRented(false);
            System.out.println("Użytkownik " + login + " zwrócił pojazd: " + rentedCar);
            this.rentedCar = null;
        } else {
            System.out.println("Nie masz wypożyczonego pojazdu.");
        }
    }

    public String toCSV() {
        return login + ";" + password + ";" + role + ";" + (rentedCar != null ? rentedCar.getId() : "brak");
    }

    public void setRentedCar(Vehicle car) {
        this.rentedCar = car;
        if (car != null) {
            car.setRented(true);
        }
    }

    @Override
    public String toString() {
        return "User{login='" + login + "', role='" + role + "', rentedCar=" + (rentedCar != null ? rentedCar.getId() : "brak") + "}";
    }
}
