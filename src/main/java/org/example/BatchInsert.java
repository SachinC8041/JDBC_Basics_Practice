package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BatchInsert
{
    public static void main(String[] args)
    {

            try (
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/JDBC_Student_Management",
                            "root",
                            "password1234"
                    );

                    PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO BatchStudent (student_first_name, student_last_name) VALUES (?, ?)"
                    );
            ) {

                ps.setString(1, "A");
                ps.setString(2, "One");
                ps.addBatch();

                ps.setString(1, "B");
                ps.setString(2, "Two");
                ps.addBatch();

                ps.setString(1, "C");
                ps.setString(2, "Three");
                ps.addBatch();

                int[] result = ps.executeBatch();

                System.out.println("Inserted: " + result.length + " rows");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
