package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_Operations
{
    // FETCH
    public static void fetchData(Connection connection) {
        try {
            String query = "SELECT * FROM Students";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Id: " + rs.getInt("student_id") +
                                ", First Name: " + rs.getString("student_first_name") +
                                ", Last Name: " + rs.getString("student_last_name")
                );
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateData(Connection con, Scanner sc )
    {
        try
        {
            System.out.println("Enter the ID to update : ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter new First Name: ");
            String fname = sc.nextLine();

            String query = "UPDATE Students SET student_first_name = ? WHERE student_id = ? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, fname);
            ps.setInt(2,id);
            int rows = ps.executeUpdate();
            System.out.println(rows + " rows Updated.");
            ps.close();

        }catch( Exception e){
            e.printStackTrace();
        }
        
    }

    public static void deleteData(Connection con , Scanner sc)
    {
        try{
            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();
            String query = "DELETE FROM Students WHERE student_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows + " record deleted.");

            ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //Insert dAta
    public static void insertData(Connection con, Scanner sc) {
        try {
            sc.nextLine();
            System.out.print("Enter First Name: ");
            String fname = sc.nextLine();

            System.out.print("Enter Last Name: ");
            String lname = sc.nextLine();


            String query = "INSERT INTO Students (student_first_name , student_last_name ) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, fname);
            ps.setString(2, lname);

            int rows = ps.executeUpdate();
            System.out.println(rows + " record inserted.");

            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
