package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.repositories.IQLDepartment;
import models.ConnectionDetail;
import models.Department;

public class QLDepartmentImpl implements IQLDepartment {

    @Override
    public List<Department> displayDepartment(ConnectionDetail detail) {
            List<Department> departments = new ArrayList<>();
            try {
                Connection connection = DriverManager.getConnection(detail.getUrl(), detail.getUsername(),
                        detail.getPassword());
                if (connection == null) {
                    System.out.println("No Db connection");
                    return null;
                }
                System.out.println("Access DB successfully, getting data...");

                String sqlQuery = "Select * from department";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()){
                    Department department = new Department(
                        resultSet.getInt("department_id"),
                        resultSet.getString("department_name")
                    );
                    departments.add(department);
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }

            return departments;    
    }

}
