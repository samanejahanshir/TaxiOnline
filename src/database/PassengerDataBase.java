package database;

import database.DataBaseAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import person.Driver;
import person.Passenger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassengerDataBase extends DataBaseAccess {
    public PassengerDataBase() throws ClassNotFoundException, SQLException {
        super();
    }
    public List<Passenger> getListPassengers() throws SQLException {
        List<Passenger> passengers;
       /* if (getConnection()!=null){
            Statement statement=getConnection().createStatement();
            String sqlQuery=String.format("SELECT * FROM passenger");
            ResultSet resultSet=statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                Passenger passenger=new Passenger(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getBoolean(5),resultSet.getString(6),resultSet.getString(9),resultSet.getDouble(8),resultSet.getBoolean(7));
               passenger.setId(resultSet.getInt(1));
                passengers.add(passenger);
            }
            return passengers;
        }
        return  null;*/
        Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Passenger> criteria = builder.createQuery(Passenger.class);
        criteria.from(Passenger.class);
        passengers = session.createQuery(criteria).getResultList();
        return passengers;
    }
    public int updatePassengerStatus(Passenger passenger) throws SQLException {
      /*  if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("UPDATE passenger SET attendance_status = %b WHERE idpassenger=%d", passenger.isAttendanceStatus(), passenger.getId());
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
        session.update(passenger);
        transaction.commit();
        session.close();
        return 1;

    }
    public int save(Passenger passenger) throws SQLException {
       /* if(getConnection()!=null){
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
        return 0;*/
        Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(passenger);
        transaction.commit();
        session.close();
        return id;
    }
    public Passenger searchPassenger(String national_code) throws SQLException {
       /* if (getConnection() != null) {
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
        }*/
       /* Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Passenger> criteria = builder.createQuery(Passenger.class);
        criteria.from(Passenger.class);
        List<Passenger> passengerList = session.createQuery(criteria).getResultList();
        Passenger passenger = passengerList.stream().filter(passenger1 -> passenger1.getNationalCode().equalsIgnoreCase(national_code)).findAny().get();
        System.out.println(passenger);
        transaction.commit();
        session.close();
        return passenger;*/
        Session session=DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query<Passenger> query = session.createQuery("from Passenger p where p.nationalCode=:nationalCode", Passenger.class);
        query.setParameter("nationalCode",national_code);
        List<Passenger> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList.get(0);
    }
    public boolean changeBalance(String national_code, double balance) throws SQLException {
       /* if (getConnection() != null) {
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
        }*/
        Passenger passenger=searchPassenger(national_code);
        passenger.setBalance(balance);
        Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(passenger);
        transaction.commit();
        session.close();
        return true;

    }
    public Passenger searchPassengerById(int id) throws SQLException {
        Session session=DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query<Passenger> query = session.createQuery("from Passenger d where d.id=:id",Passenger.class);
        query.setParameter("id",id);
        List<Passenger> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList.get(0);
    }
    public double showBalance(String nationalCode) throws SQLException {
       /* if (getConnection() != null) {
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
        }*/
       return searchPassenger(nationalCode).getBalance();

    }
}
