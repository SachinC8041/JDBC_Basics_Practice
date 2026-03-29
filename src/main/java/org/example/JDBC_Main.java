package org.example;

import java.sql.*;
import java.util.Scanner;

public class JDBC_Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 1 to fetch data, Enter 2 to update data:");
        int choice = sc.nextInt();

        String url = "jdbc:mysql://localhost:3306/jdbc_student_management";
        String username = "root";
        String password = "password1234";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Successful");

            Statement st = con.createStatement();

            switch (choice) {
                case 1:
                    JDBC_Operations.fetchData(con, st);
                    break;

                case 2:
                    System.out.println("Update feature not implemented yet");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}