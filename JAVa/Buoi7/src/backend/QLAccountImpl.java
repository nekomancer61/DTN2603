package backend;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import backend.repositories.IQLAccount;
import models.Account;
import models.ConnectionDetail;

public class QLAccountImpl implements IQLAccount {

    Connection connection;

    public QLAccountImpl(ConnectionDetail detail) {
        try {
            this.connection = DriverManager.getConnection(
                    detail.getUrl(),
                    detail.getUsername(),
                    detail.getPassword());
            if (this.connection == null) {
                System.out.println("No Db connection");
            }
            System.out.println("Access DB successfully, getting data...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Account> displayAccount() {
        List<Account> accounts = new ArrayList<>();
        try {
            String sqlQuery = "Select * from account";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Account account = new Account(
                        resultSet.getInt("account_id"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("fullname"),
                        resultSet.getDate("create_date"));
                accounts.add(account);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return accounts;
    }

    @Override
    public void insertAccount(int accountId, String email, String userName, String fullName) {
        try {
            String insertQuery = "insert into account (account_id, email, username, fullname, create_date) values (?,?,?,?,?);";
            PreparedStatement prpStatement = connection.prepareStatement(insertQuery);
            prpStatement.setInt(1, accountId);
            prpStatement.setString(2, email);
            prpStatement.setString(3, userName);
            prpStatement.setString(4, fullName);
            Date today = Date.valueOf(LocalDate.now());
            prpStatement.setDate(5, today);

            int rowAffected = prpStatement.executeUpdate();
            System.out.println(rowAffected+" rows affected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(int accountId, Account account) {
        try {
            String updateQuery = "UPDATE `account` SET email = ?, username = ?, fullname = ?  WHERE (account_id = ?); ";
            PreparedStatement prpStatement = connection.prepareStatement(updateQuery);
            
            prpStatement.setString(1, account.getEmail());
            prpStatement.setString(2, account.getUserName());
            prpStatement.setString(3, account.getFullName());
            prpStatement.setInt(4, accountId);

            int rowAffected = prpStatement.executeUpdate();
            System.out.println(rowAffected+" rows affected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(int accountId) {
        try {
            String deleteQuery = "DELETE FROM account WHERE (account_id = ?); ";
            PreparedStatement prpStatement = connection.prepareStatement(deleteQuery);
            
            prpStatement.setInt(1, accountId);

            int rowAffected = prpStatement.executeUpdate();
            System.out.println(rowAffected+" rows affected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
