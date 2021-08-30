package com.company.Controllers;

import com.company.DbHelper.DbConnection;
import com.company.Menu.Menu;
import com.company.Objects.Busses;
import com.company.Objects.Drivers;
import com.company.Objects.Routes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DriverController {
    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean addNewDriver(){

        System.out.print("Enter driver name: ");
        String name = scanner.nextLine();

        System.out.print("Enter driver surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter driver status: ");
        String status = scanner.nextLine();

        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO drivers(name, surname, status) VALUES('" + name + "', '" + surname + "', '" + status + "')");
            ps.execute();
            System.out.println("Successfully added new driver \n");
            Menu.menuAdmin();
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.print("Failed to add new driver, try again (Y/N)? ");
            String option = scanner.next();
            switch (option){
                case "Y":
                    addNewDriver();
                    break;
                case "N":
                    System.out.println("Good bye");
                    break;
                default:
                    System.out.println("Invalid input, start over");
            }
            return false;
        }
    }

    public static Drivers seeDriverList(){
        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM drivers");
            rs = ps.executeQuery();

            int driverId, assigned_bus, assigned_route;
            String name, surname, status;

            Drivers driver = new Drivers();

            System.out.println("The drivers are: ");
            System.out.printf("%-5s %-10s %-10s %-10s %-15s %-15s\n", "id", "name", "surname", "status", "assigned_bus", "assigned_route");

            while (rs.next()){
                driverId = rs.getInt("driver_ID");
                name = rs.getString("name");
                surname = rs.getString("surname");
                status = rs.getString("status");
                assigned_bus = rs.getInt("assigned_bus");
                assigned_route = rs.getInt("assigned_route");

                driver.setId(driverId);
                driver.setName(name);
                driver.setSurname(surname);
                driver.setStatus(status);
                driver.setAssigned_bus(assigned_bus);
                driver.setAssigned_route(assigned_route);

                System.out.printf("%-5s %-10s %-10s %-10s %-15s %-15s\n", driverId, name, surname, status, assigned_bus, assigned_route);
            }
            System.out.println();
            Menu.menuAdmin();
            return driver;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Failed to get the drivers list");
            return null;
        }
    }

    public static void assignBusToDriver() {
        System.out.println("Available drivers are: ");
        System.out.printf("%-5s %-10s %-10s\n", "id", "name", "surname");


        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM drivers WHERE status='free'");
            rs = ps.executeQuery();

            int id;
            String name, surname;

            Drivers driver = new Drivers();

            while (rs.next()) {
                id = rs.getInt("driver_ID");
                name = rs.getString("name");
                surname = rs.getString("surname");
                driver.setId(id);
                driver.setName(name);
                driver.setSurname(surname);
                System.out.printf("%-5s %-10s %-10s\n", id, name, surname);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Enter driver's id for assigning a bus: ");
        int driver_id = scanner.nextInt();

        System.out.println("Available busses are: ");
        System.out.printf("%-10s\n", "VIN_number");


        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM busses WHERE status='free'");
            rs = ps.executeQuery();

            int number;

            Busses bus = new Busses();

            while (rs.next()) {
                number = rs.getInt("VIN_number");
                bus.setVIN_number(number);
                System.out.printf("%-10s\n", number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Enter bus's VIN_number for assigning to the driver: ");
        int vinNumber = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("UPDATE drivers SET assigned_bus= " + vinNumber + " WHERE driver_ID= " + driver_id);
            ps.execute();
            System.out.println("Successfully assigned bus to the driver");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to assign the bus to the driver");
        }

        try {
            ps = DbConnection.getConnection().prepareStatement("UPDATE busses SET status='active' WHERE VIN_number= " + vinNumber);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        changeDriverStatus();
        System.out.println();
        Menu.menuAdmin();
    }

    public static void assignRouteToDriver() {
        System.out.println("Available drivers are: ");
        System.out.printf("%-5s %-10s %-10s\n", "id", "name", "surname");

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM drivers WHERE status='free'");
            rs = ps.executeQuery();

            int id;
            String name, surname;

            Drivers driver = new Drivers();

            while (rs.next()) {
                id = rs.getInt("driver_ID");
                name = rs.getString("name");
                surname = rs.getString("surname");
                driver.setId(id);
                driver.setName(name);
                driver.setSurname(surname);
                System.out.printf("%-5s %-10s %-10s\n", id, name, surname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Enter driver's id for assigning a route: ");
        int driver_id = scanner.nextInt();

        System.out.println("The routes are: ");
        System.out.printf("%-10s %-10s\n", "route_number", "route_name");

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM routes");
            rs = ps.executeQuery();

            int route_number;
            String route_name;

            Routes route = new Routes();

            while (rs.next()){
                route_number = rs.getInt("route_number");
                route_name = rs.getString("route_name");
                route.setRoute_number(route_number);
                route.setRoute_name(route_name);
                System.out.printf("%-10s %-10s\n", route_number, route_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Enter route number for assigning to the driver: ");
        int routeNumber = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("UPDATE drivers SET assigned_route= " + routeNumber + " WHERE driver_ID= " + driver_id);
            ps.execute();
            System.out.println("Successfully assigned route to the driver");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to assign the route to the driver");
        }

        changeDriverStatus();
        System.out.println();
        Menu.menuAdmin();
    }

    public static void changeDriverStatus(){
        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM drivers");
            rs = ps.executeQuery();

            String busCheck = "";
            String routeCheck = "";

            while (rs.next()) {
                busCheck = rs.getString("assigned_bus");
                routeCheck = rs.getString("assigned_route");
                if (busCheck != null & routeCheck != null) {
                    try {
                        ps = DbConnection.getConnection().prepareStatement("UPDATE drivers SET status='active' WHERE assigned_bus= " + busCheck);
                        ps.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
