package com.company.Controllers;

import com.company.DbHelper.DbConnection;
import com.company.Menu.Menu;
import com.company.Objects.Busses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BusController {
    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean addNewBus(){

        System.out.print("Enter the bus VIN number(5 digits): ");
        int number = scanner.nextInt();

        System.out.print("Enter the bus status: ");
        scanner.nextLine();
        String status = scanner.nextLine();

        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO busses(VIN_number, status) VALUES('" + number + "', '" + status + "')");
            ps.execute();
            System.out.println("Successfully added new bus \n");
            Menu.menuAdmin();
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.print("Failed to add new bus, try again (Y/N)? ");
            String option = scanner.next();
            switch (option){
                case "Y":
                    addNewBus();
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

    public static Busses seeBusList(){
        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM busses");
            rs = ps.executeQuery();

            int busId, vin_number;
            String status;

            Busses bus = new Busses();

            System.out.println("The busses are: ");
            System.out.println("id \t VIN_number \t status");

            while (rs.next()){
                busId = rs.getInt("ID");
                vin_number = rs.getInt("VIN_number");
                status = rs.getString("status");
                bus.setId(busId);
                bus.setVIN_number(vin_number);
                bus.setStatus(status);
                System.out.println(busId + "\t" + vin_number + "\t" +status);
            }
            System.out.println();
            Menu.menuAdmin();
            return bus;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Failed to get the bus list");
            return null;
        }
    }
}
