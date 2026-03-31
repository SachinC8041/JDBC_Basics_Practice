package org.example;

import java.sql.*;
import java.util.Scanner;

public class JDBC_Main {
    public static final String url = "jdbc:mysql://localhost:3306/jdbc_student_management";
    public static final String username = "root";
    public static final String password = "password1234";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("1. Fetch Data");
        System.out.println("2. Update Data");
        System.out.println("3. Delete Data");
        System.out.println("4. Insert Data");
        int choice = sc.nextInt();

        try (Connection con = DriverManager.getConnection(url, username, password)) {

            System.out.println("Connection Successful ✅");

            switch (choice) {

                case 1:
                    JDBC_Operations.fetchData(con);
                    break;

                case 2:
                    JDBC_Operations.updateData(con, sc);
                    break;

                case 3:
                    JDBC_Operations.deleteData(con, sc);
                    break;

                case 4:
                    JDBC_Operations.insertData(con, sc);
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}