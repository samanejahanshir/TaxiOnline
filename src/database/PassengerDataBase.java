package database;

import person.Driver;
import person.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassengerDataBase extends DataBaseAccess{
    public PassengerDataBase() throws ClassNotFoundException, SQLException {
        super();
    }
    public List<Passenger> showListPassengers() throws SQLException {
        List<Passenger> passengers=new ArrayList<>();
        if (getConnection()!=null){
            Statement statement=getConnection().createStatement();
            String sqlQuery=String.format("SELECT * FROM passenger");
            ResultSet resultSet=statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                Passenger passenger=new Passenger(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getBoolean(5),resultSet.getString(6),resultSet.getString(9),resultSet.getDouble(8),resultSet.getBoolean(7));
                passengers.add(passenger);
            }
            return passengers;
        }
        return  null;
    }
}
