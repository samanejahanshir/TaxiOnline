package database;

import other_class.Travel;
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
            String sqlQuery = String.format("INSERT INTO driver (first_name , last_name , national_code , mobile_phone , gender , birth_date , car_tag , balance , origin) VALUES ('%s','%s','%s','%s',%b,'%s','%s',%f,'%s')",
                    driver.getFirstName(), driver.getLastName(), driver.getNationalCode(), driver.getPhoneNumber(), driver.isGender(), driver.getBirthDate(), driver.getCarTag(), driver.getBalance(),driver.getOrigin());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }
    }

    public int updateDriver(Driver driver) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("UPDATE driver SET origin = '%s' WHERE national_code='%s'", driver.getOrigin(),driver.getNationalCode());
            int i = statement.executeUpdate(sqlQuery);
            if (i != 0) {
                return i;
            } else {
                return -1;
            }

        }
        return -1;
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

    public List<Driver> getListDrivers() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("SELECT * FROM driver");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Driver driver = new Driver(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                        , resultSet.getBoolean(6), resultSet.getString(7), resultSet.getString(5), resultSet.getDouble(9), resultSet.getString(8),resultSet.getString(10),resultSet.getBoolean(11));
                driver.setId(resultSet.getInt(1));
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
