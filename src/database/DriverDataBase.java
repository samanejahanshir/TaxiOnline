package database;

import person.Driver;

import java.sql.SQLException;
import java.sql.Statement;

public class DriverDataBase extends  DataBaseAccess{
    public DriverDataBase() throws ClassNotFoundException, SQLException {
    }
    public int save(Driver driver) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO driver (first_name , last_name , national_code , mobile_phone , gender , birth_date , car_tag , balance) VALUES ('%s','%s','%s','%s',%d,'%s','%s',%d)",
                    driver.getFirstName(), driver.getLastName(), driver.getNationalCode(), driver.getPhoneNumber(), driver.isGender(),driver.getBirthDate(),driver.getCarTag(),driver.getBalance());
            int i = statement.executeUpdate(sqlQuery);
           return i;
        } else {
            return 0;
        }
    }

}
