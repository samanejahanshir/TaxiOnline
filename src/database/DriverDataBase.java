package database;

import other_class.Vehicle;
import person.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DriverDataBase extends DataBaseAccess {
    public DriverDataBase() throws ClassNotFoundException, SQLException {
        super();
    }

    public int save(Driver driver) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO driver (first_name , last_name , national_code , mobile_phone , gender , birth_date , car_tag , balance) VALUES ('%s','%s','%s','%s',%b,'%s','%s',%f)",
                    driver.getFirstName(), driver.getLastName(), driver.getNationalCode(), driver.getPhoneNumber(), driver.isGender(), driver.getBirthDate(), driver.getCarTag(), driver.getBalance());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }

    public int searchDriver(String national_code) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("SELECT id_driver from driver WHERE national_code='%s'", national_code);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public List<Driver> showListDrivers() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("SELECT * FROM driver");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Driver driver = new Driver(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                        , resultSet.getBoolean(6), resultSet.getString(7), resultSet.getString(5), resultSet.getDouble(9), resultSet.getString(8));
                drivers.add(driver);
            }
            return drivers;
        }
        return null;
    }

    public double showBalance(String nationalCode) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("SELECT balance from driver WHERE national_code='%s'", nationalCode);
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
    public int saveVehicle(Vehicle vehicle) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO vehicle (vehicle_tag , type , model , color) VALUES ('%s','%s','%s','%s')",
                    vehicle.getVehicleTag(), vehicle.getType(), vehicle.getModel(), vehicle.getColor());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }
}
