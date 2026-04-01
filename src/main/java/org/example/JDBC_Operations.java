package org.example;

import java.sql.*;
import java.util.Scanner;

public class JDBC_Operations {

    // FETCH
    public static void fetchData(Connection con) {
        String query = "SELECT * FROM Students";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        "Id: " + rs.getInt("student_id") +
                                ", First Name: " + rs.getString("student_first_name") +
                                ", Last Name: " + rs.getString("student_last_name")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching data ");
            e.printStackTrace();
        }
    }

    // UPDATE
    public static void updateData(Connection con, Scanner sc) {
        String query = "UPDATE Students SET student_first_name = ? WHERE student_id = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {

            System.out.print("Enter ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter new First Name: ");
            String fname = sc.nextLine();

            ps.setString(1, fname);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Updated " : "ID not found ");

        } catch (SQLException e) {
            System.out.println("Error updating data ");
            e.printStackTrace();
        }
    }

    //  DELETE
    public static void deleteData(Connection con, Scanner sc) {
        String query = "DELETE FROM Students WHERE student_id = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {

            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Deleted " : "ID not found ");

        } catch (SQLException e) {
            System.out.println("Error deleting data ");
            e.printStackTrace();
        }
    }

    //  INSERT
    public static void insertData(Connection con, Scanner sc) {
        String query = "INSERT INTO Students (student_first_name, student_last_name) VALUES (?, ?)";

        try (PreparedStatement ps = con.prepareStatement(query)) {

            sc.nextLine(); // clear buffer

            System.out.print("Enter First Name: ");
            String fname = sc.nextLine();

            System.out.print("Enter Last Name: ");
            String lname = sc.nextLine();

            ps.setString(1, fname);
            ps.setString(2, lname);

            int rows = ps.executeUpdate();
            System.out.println(rows + " record inserted ");

        } catch (SQLException e) {
            System.out.println("Error inserting data ");
            e.printStackTrace();
        }
    }
}