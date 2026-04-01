package org.example.transactionpractice;

import java.sql.*;

public class AccountService {

    public static final String url = "jdbc:mysql://localhost:3306/JDBC_Student_Management";
    public static final String username = "root";
    public static final String password = "password1234";

    public void transferMoney(int fromAccountId, int toAccountId, double amount) {

        String debitQuery = "UPDATE TransactionPractice SET balance = balance - ? WHERE id = ?";
        String creditQuery = "UPDATE TransactionPractice SET balance = balance + ? WHERE id = ?";

        Connection con = null;

        try {
            con = DriverManager.getConnection(url, username, password);
            con.setAutoCommit(false);

            try (PreparedStatement debitPS = con.prepareStatement(debitQuery);
                 PreparedStatement creditPS = con.prepareStatement(creditQuery)) {

                // 🔹 Debit
                debitPS.setDouble(1, amount);
                debitPS.setInt(2, fromAccountId);
                int debitRows = debitPS.executeUpdate();

                if (debitRows == 0) {
                    throw new RuntimeException("Debit failed: Account not found");
                }

                // 🔹 Savepoint
                Savepoint savepoint = con.setSavepoint("AfterDebit");

                // 🔹 Credit
                creditPS.setDouble(1, amount);
                creditPS.setInt(2, toAccountId);
                int creditRows = creditPS.executeUpdate();

                if (creditRows == 0) {
                    con.rollback(savepoint);
                    throw new RuntimeException("Credit failed: Account not found");
                }

                con.commit();
                System.out.println("Transaction successful ✅");

            }

        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback(); // 🔥 VERY IMPORTANT
                    System.out.println("Transaction rolled back");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertAccount() {
        String query = "INSERT INTO TransactionPractice (fname,balance) VALUES(?,?)";

        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, "Saurav");
            ps.setDouble(2, 12999);
            ps.addBatch();

            ps.setString(1, "Virendra");
            ps.setDouble(2, 19000);
            ps.addBatch();

            int[] rows = ps.executeBatch();
            System.out.println(rows.length > 0 ? "Inserted " : "Not Inserted");

        } catch (SQLException sq) {
            System.out.println("Error Occured");
            sq.printStackTrace();
        }
    }

    public void fetchAccountDetails() {
        String query = "SELECT * FROM TransactionPractice";
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println("__________________________________");
                System.out.println("Id : " + rs.getInt("id") + " firstName : " + rs.getString("fname")
                        + " amount in bank : " + rs.getDouble("balance"));
            }
        } catch (Exception e) {
            System.out.println("Error Occured");
            e.printStackTrace();
        }

    }
}