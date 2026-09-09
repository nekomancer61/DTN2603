package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.repositories.IQLPosition;
import models.ConnectionDetail;
import models.Position;
import models.Position.PositionName;

public class QLPositionImpl implements IQLPosition{

    @Override
    public List<Position> displayPosition(ConnectionDetail detail) {
        List<Position> positions = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(detail.getUrl(), detail.getUsername(),
                    detail.getPassword());
            if (connection == null) {
                System.out.println("No Db connection");
                return null;
            }
            System.out.println("Access DB successfully, getting data...");

            String sqlQuery = "Select * from position";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                Position position = new Position(
                    resultSet.getInt("position_id"),
                    PositionName.valueOf(resultSet.getString("position_name"))
                );
                positions.add(position);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return positions;    
    }

}
