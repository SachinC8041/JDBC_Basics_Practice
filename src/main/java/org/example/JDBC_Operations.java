package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_Operations
{
    public static void fetchData(Connection connection, Statement st) throws Exception
    {
        ResultSet rs = st.executeQuery("SELECT * FROM Students");
        while (rs.next()) {
            System.out.println(
                    "Id: " + rs.getInt("student_id") +
                            ", First Name: " + rs.getString("student_first_name") +
                            ", Last Name: " + rs.getString("student_last_name")
            );
        }
    }

    public static void updateData(Connection con, Statement st) throws Exception
    {

    }
}
