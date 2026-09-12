package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.repositories.IQLDepartment;
import models.ConnectionDetail;
import models.Department;

public class QLDepartmentImpl implements IQLDepartment {
    Connection connection;

    public QLDepartmentImpl(ConnectionDetail detail) {
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
    public List<Department> displayDepartment() {
        List<Department> departments = new ArrayList<>();
        try {

            String sqlQuery = "Select * from department";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Department department = new Department(
                        resultSet.getInt("department_id"),
                        resultSet.getString("department_name"));
                departments.add(department);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return departments;
    }

    @Override
    public void insertDepartment(int departmentId, String departmentName) {
        try {
            String insertQuery = "insert into department (department_id, department_name) values (?,?);";
            PreparedStatement prpStatement = connection.prepareStatement(insertQuery);
            prpStatement.setInt(1, departmentId);
            prpStatement.setString(2, departmentName);

            int rowAffected = prpStatement.executeUpdate();
            System.out.println(rowAffected + " rows affected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDepartment(int department_id, String departmentName) {
        try {
            String updateQuery = "UPDATE `department` SET department_name = ? WHERE (department_id = ?); ";
            PreparedStatement prpStatement = connection.prepareStatement(updateQuery);

            prpStatement.setString(1, departmentName);
            prpStatement.setInt(2, department_id);

            int rowAffected = prpStatement.executeUpdate();
            System.out.println(rowAffected + " rows affected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDepartment(int departmentId) {
        try {

            String deleteAccQuery = "Delete from account where (department_id = ?);";
            PreparedStatement preparedStatementAcc = connection.prepareStatement(deleteAccQuery);
            preparedStatementAcc.setInt(1, departmentId);
            int rowAffected = preparedStatementAcc.executeUpdate();
            System.out.println(rowAffected + " rows of table account affected");

            String deleteQuery = "DELETE FROM department WHERE (department_id = ?); ";
            PreparedStatement prpStatement = connection.prepareStatement(deleteQuery);
            
            prpStatement.setInt(1, departmentId);

            rowAffected = prpStatement.executeUpdate();
            System.out.println(rowAffected+" rows affected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
