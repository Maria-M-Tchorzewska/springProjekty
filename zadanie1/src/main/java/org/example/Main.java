package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IVehicleRepository repo = new VehicleRepository();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("MENU :");
            System.out.println("1. Wyświetl pojazdy");
            System.out.println("2. Wypożycz pojazd");
            System.out.println("3. Zwróć pojazd");
            System.out.println("4. Zakończ");

            System.out.print("Wybierz opcję: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    for (Vehicle v : repo.getVehicles()) {
                        System.out.println(v);
                    }
                    break;
                case 2:
                    System.out.print("Podaj ID pojazdu do wypożyczenia: ");
                    int idRent = scanner.nextInt();
                    repo.rentVehicle(idRent);
                    break;
                case 3:
                    System.out.print("Podaj ID pojazdu do zwrotu: ");
                    int idReturn = scanner.nextInt();
                    repo.returnVehicle(idReturn);
                    break;
                case 4:
                    System.out.println("Koniec programu.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja.");
            }
        }
    }
}
