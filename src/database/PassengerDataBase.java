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
    public int save(Passenger passenger) throws SQLException {
        if(getConnection()!=null){
            Statement statement= getConnection().createStatement();
            String sqlQuery=String.format("INSERT INTO passenger (first_name , last_name , national_code , gender , birth_date , attendance_status , balance , mobile_phone)"
                    +"VALUES ('%s','%s','%s',%b,'%s',%b,%f,'%s')",passenger.getFirstName(),passenger.getLastName(),passenger.getNationalCode(),passenger.isGender(),passenger.getBirthDate(),passenger.isAttendanceStatus(),passenger.getBalance(),passenger.getPhoneNumber());
            int i=statement.executeUpdate(sqlQuery);
            if(i!=0){
                return i;
            }else {
                return 0;
            }
        }
        return 0;
    }
    public int searchPassenger(String national_code) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery= String.format("SELECT national_code from passenger WHERE national_code='%s'",national_code);
            ResultSet resultSet=statement.executeQuery(sqlQuery);
            if(resultSet.next()){
                return  resultSet.getInt(1);
            }else {
                return -1;
            }
        }else {
            return  -1;
        }
    }
    public boolean IncrementBalance(String national_code, double balance) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("UPDATE passenger SET balance=%2f WHERE national_code='%s'", balance, national_code);
            int i = statement.executeUpdate(sqlQuery);
            if (i != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public double showBalance(String nationalCode) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("SELECT balance from passenger WHERE national_code='%s'", nationalCode);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
