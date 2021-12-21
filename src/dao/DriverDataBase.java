package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import models.Vehicle;
import models.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DriverDataBase extends DataBaseAccess {
    public DriverDataBase() throws ClassNotFoundException, SQLException {
        super();
    }

    public int save(Driver driver) throws SQLException {
       /* if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO driver (first_name , last_name , national_code , mobile_phone , gender , birth_date , car_tag , balance , origin) VALUES ('%s','%s','%s','%s',%b,'%s','%s',%f,'%s')",
                    driver.getFirstName(), driver.getLastName(), driver.getNationalCode(), driver.getPhoneNumber(), driver.isGender(), driver.getBirthDate(), driver.getCarTag(), driver.getBalance(), driver.getOrigin());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }*/
        Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(driver);
        transaction.commit();
        session.close();
        return id;
    }
   /* public int updateDriverStatus(Driver driver) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("UPDATE driver SET status = %b WHERE id_driver=%d", driver.getStatus(), driver.getId());
            int i = statement.executeUpdate(sqlQuery);
            if (i != 0) {
                return i;
            } else {
                return -1;
            }

        }
        return -1;*//*
        Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(driver);
        transaction.commit();
        session.close();
        return 1;

    }*/

    public int updateDriver(Driver driver) throws SQLException {
        /*if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("UPDATE driver SET origin = '%s' WHERE national_code='%s'", driver.getOrigin(), driver.getNationalCode());
            int i = statement.executeUpdate(sqlQuery);
            if (i != 0) {
                return i;
            } else {
                return -1;
            }

        }
        return -1;*/
        Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Driver driver1=searchDriver(driver.getNationalCode());
        driver1.setStatus(1);
        session.update(driver1);
        transaction.commit();
        session.close();
        return 1;
    }

    public Driver searchDriver(String national_code) throws SQLException {
       /* if (getConnection() != null) {
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
        }*/
       /* Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Driver> criteria = builder.createQuery(Driver.class);
        criteria.from(Passenger.class);
        List<Driver> driverList = session.createQuery(criteria).getResultList();
        Driver driver = driverList.stream().filter(passenger1 -> passenger1.getNationalCode().equalsIgnoreCase(national_code)).findAny().get();
        System.out.println(driver);
        transaction.commit();
        session.close();
        return driver;*/
        Session session=DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query<Driver> query = session.createQuery("from Driver d where d.nationalCode=:nationalCode", Driver.class);
        query.setParameter("nationalCode",national_code);
        List<Driver> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList.get(0);
    }
    public Driver searchDriverById(int id) throws SQLException {
        Session session=DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query<Driver> query = session.createQuery("from Driver d where d.id=:id", Driver.class);
        query.setParameter("id",id);
        List<Driver> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList.get(0);
    }
    public List<Driver> getListDrivers() throws SQLException {
        List<Driver> drivers ;
        /*if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("SELECT * FROM driver");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Driver driver = new Driver(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                        , resultSet.getBoolean(6), resultSet.getString(7), resultSet.getString(5), resultSet.getDouble(9), resultSet.getString(8), resultSet.getString(10), resultSet.getBoolean(11));
                driver.setId(resultSet.getInt(1));
                drivers.add(driver);
            }
            return drivers;
        }
        return null;*/
       /* Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Driver> criteria = builder.createQuery(Driver.class);
        criteria.from(Driver.class);
        drivers = session.createQuery(criteria).getResultList();
        return drivers;*/
        Session session=DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query<Driver> query = session.createQuery("from Driver", Driver.class);
        List<Driver> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
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
       /* if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO vehicle (vehicle_tag , type , model , color) VALUES ('%s','%s','%s','%s')",
                    vehicle.getVehicleTag(), vehicle.getType(), vehicle.getModel(), vehicle.getColor());
            int i = statement.executeUpdate(sqlQuery);
            return i;
        } else {
            return 0;
        }*/
        Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(vehicle);
        transaction.commit();
        session.close();
        return id;
    }

    public Vehicle getVehicle(String carTag) throws SQLException {

       /* if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("SELECT * FROM vehicle WHERE vehicle_tag='%s'", carTag);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                Vehicle vehicle = new Vehicle(carTag, resultSet.getString(5), resultSet.getString(4), resultSet.getString(3));
                vehicle.setId(resultSet.getInt(1));
                return vehicle;
            }
        }
        return null;*/
      /*  Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Vehicle> criteria = builder.createQuery(Vehicle.class);
        criteria.from(Vehicle.class);
        List<Vehicle> passengerList = session.createQuery(criteria).getResultList();
        Vehicle vehicle = passengerList.stream().filter(vehicle1 -> vehicle1.getVehicleTag().equalsIgnoreCase(carTag)).findAny().get();
        System.out.println(vehicle);
        transaction.commit();
        session.close();
        return vehicle;*/
        Session session=DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query<Vehicle> query = session.createQuery("from Vehicle d where d.vehicleTag=:carTag",Vehicle.class);
        query.setParameter("carTag",carTag);
        List<Vehicle> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList.get(0);
    }

    public boolean changeBalance(String national_code, double balance) throws SQLException {
       /* if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("UPDATE driver SET balance=%2f WHERE national_code='%s'", balance, national_code);
            int i = statement.executeUpdate(sqlQuery);
            if (i != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }*/
        Driver driver=searchDriver(national_code);
        driver.setBalance(balance);
        updateDriver(driver);
        return  true;
    }

}
