package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.repositories.IQLAccount;
import models.Account;
import models.ConnectionDetail;

public class QLAccountImpl implements IQLAccount {

    @Override
    public List<Account> displayAccount(ConnectionDetail detail) {
        List<Account> accounts = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(detail.getUrl(), detail.getUsername(),
                    detail.getPassword());
            if (connection == null) {
                System.out.println("No Db connection");
                return null;
            }
            System.out.println("Access DB successfully, getting data...");

            String sqlQuery = "Select * from account";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                Account account = new Account(
                    resultSet.getInt("account_id"),
                    resultSet.getString("email"),
                    resultSet.getString("username"),
                    resultSet.getString("fullname"),
                    resultSet.getDate("create_date")
                );
                accounts.add(account);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return accounts;
    }

}
