package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.repositories.IQLPosition;
import models.ConnectionDetail;
import models.Position;
import models.Position.PositionName;

public class QLPositionImpl implements IQLPosition{
    Connection connection;

    public QLPositionImpl(ConnectionDetail detail) {
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
    public List<Position> displayPosition() {
        List<Position> positions = new ArrayList<>();
        try {

            String sqlQuery = "Select * from position";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Position position = new Position(
                        resultSet.getInt("position_id"),
                        PositionName.valueOf(resultSet.getString("position_name")));
                positions.add(position);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return positions;
    }

    @Override
    public void insertPosition(int positionId, String positionName) {
        try {
            String insertQuery = "insert into position (position_id, position_name) values (?,?);";
            PreparedStatement prpStatement = connection.prepareStatement(insertQuery);
            prpStatement.setInt(1, positionId);
            prpStatement.setString(2, positionName);

            int rowAffected = prpStatement.executeUpdate();
            System.out.println(rowAffected + " rows affected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePosition(int position_id, String positionName) {
        try {
            String updateQuery = "UPDATE `position` SET position_name = ? WHERE (position_id = ?); ";
            PreparedStatement prpStatement = connection.prepareStatement(updateQuery);

            prpStatement.setString(1, positionName);
            prpStatement.setInt(2, position_id);

            int rowAffected = prpStatement.executeUpdate();
            System.out.println(rowAffected + " rows affected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePosition(int positionId) {
        try {

            String deleteAccQuery = "Delete from account where (position_id = ?);";
            PreparedStatement preparedStatementAcc = connection.prepareStatement(deleteAccQuery);
            preparedStatementAcc.setInt(1, positionId);
            int rowAffected = preparedStatementAcc.executeUpdate();
            System.out.println(rowAffected + " rows of table account affected");

            String deleteQuery = "DELETE FROM position WHERE (position_id = ?); ";
            PreparedStatement prpStatement = connection.prepareStatement(deleteQuery);
            
            prpStatement.setInt(1, positionId);

            rowAffected = prpStatement.executeUpdate();
            System.out.println(rowAffected+" rows affected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
