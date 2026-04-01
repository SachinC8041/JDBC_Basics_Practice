package org.example.transactionpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionsPractice
{
    public static void main(String[] args) {

        AccountService service = new AccountService();

        // Example transfer
//        service.transferMoney(1, 2, 1000);
//        service.insertAccount();
        service.fetchAccountDetails();
    }
}
