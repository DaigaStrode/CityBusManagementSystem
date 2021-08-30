package com.company.Menu;

import com.company.Controllers.*;

import java.util.Scanner;

public class Menu {
    public static void menuStart(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Who are you?");
        System.out.println("1. Administrator \t 2. Passenger");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option){
            case 1:
                System.out.print("Do you have an account (Y/N)? ");
                String answer1 = scanner.next();
                switch (answer1){
                    case "Y":
                        System.out.print("Wish to log in (Y/N)? ");
                        String choice1 = scanner.next();
                        switch (choice1){
                            case "Y":
                                AdminController.logIn();
                                break;
                            case "N":
                                System.out.println("Good bye");
                                break;
                            default:
                                System.out.println("Invalid input, start over");
                        }
                        break;
                    case "N":
                        System.out.print("Wish to sign up (Y/N)? ");
                        String choice2 = scanner.next();
                        switch (choice2){
                            case "Y":
                                AdminController.signUp();
                                break;
                            case "N":
                                System.out.println("Good bye");
                                break;
                            default:
                                System.out.println("Invalid input, start over");
                        }
                        break;
                    default:
                        System.out.println("Invalid input, start over");
                }
                break;
            case 2:
                System.out.print("Do you have an account (Y/N)? ");
                String answer2 = scanner.next();
                switch (answer2){
                    case "Y":
                        System.out.print("Wish to log in (Y/N)? ");
                        String choice1 = scanner.next();
                        switch (choice1){
                            case "Y":
                                PassengerController.logIn();
                                break;
                            case "N":
                                System.out.println("Good bye");
                                break;
                            default:
                                System.out.println("Invalid input, start over");
                        }
                        break;
                    case "N":
                        System.out.print("Wish to sign up (Y/N)? ");
                        String choice2 = scanner.next();
                        switch (choice2){
                            case "Y":
                                PassengerController.signUp();
                                break;
                            case "N":
                                System.out.println("Good bye");
                                break;
                            default:
                                System.out.println("Invalid input, start over");
                        }
                        break;
                    default:
                        System.out.println("Invalid input, start over");
                }
                break;
            default:
                System.out.println("Invalid input, start over");
        }
    }

    public static void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do? ");
        System.out.println("1. Work with lists \t 2. Log out");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("\nChoose a list: ");
                System.out.println("1. Busses list \t 2. Drivers list \t 3. Routes list");
                System.out.print("Select an option: ");
                int input = scanner.nextInt();
                switch (input) {
                    case 1:
                        menuBus();
                        break;
                    case 2:
                        menuDriver();
                        break;
                    case 3:
                        menuRoute();
                        break;
                    default:
                        System.out.println("Invalid input, start over");
                }
                break;
            case 2:
                System.out.println("Good bye");
                break;
            default:
                System.out.println("Invalid input, start over");
        }
    }

    public static void menuPassenger() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do? ");
        System.out.println("1. Buy a ticket \t 2. Log out");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                PassengerController.buyTickets();
                break;
            case 2:
                System.out.println("Good bye");
                break;
            default:
                System.out.println("Invalid input, start over");
        }
    }

    public static void menuBus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat would you like to do? ");
        System.out.println("1. See bus list \t 2. Add new bus");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                BusController.seeBusList();
                break;
            case 2:
                BusController.addNewBus();
                break;
            default:
                System.out.println("Invalid input, start over");
        }
    }

    public static void menuRoute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat would you like to do? ");
        System.out.println("1. See routes list \t 2. Add new route");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                RouteController.seeRouteList();
                break;
            case 2:
                RouteController.addNewRoute();
                break;
            default:
                System.out.println("Invalid input, start over");
        }
    }

    public static void menuDriver() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat would you like to do? ");
        System.out.printf("%-30s %-30s\n", "1. See drivers list", "2. Add new driver");
        System.out.printf("%-30s %-30s\n", "3. Assign bus to driver", "4. Assign route to driver");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                DriverController.seeDriverList();
                break;
            case 2:
                DriverController.addNewDriver();
                break;
            case 3:
                DriverController.assignBusToDriver();
                break;
            case 4:
                DriverController.assignRouteToDriver();
                break;
            default:
                System.out.println("Invalid input, start over");
        }
    }
}
