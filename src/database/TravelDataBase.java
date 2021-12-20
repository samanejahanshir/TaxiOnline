package database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import other_class.Travel;
import person.Driver;
import person.Passenger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TravelDataBase extends DataBaseAccess {

    public TravelDataBase() throws ClassNotFoundException, SQLException {
        super();
    }
    public boolean findTravel(Travel travel) throws SQLException {
       /* if(getConnection()!=null){
            Statement statement=getConnection().createStatement();
            String sqlQuery=String.format("SELECT * FROM travel WHERE id_travel=%d and id_driver=%d and id_passenger=%d",travel.getId(),travel.getIdDriver(),travel.getIdPassenger());
            ResultSet resultSet=statement.executeQuery(sqlQuery);
            if(resultSet.next()){
                return true;
            }
            return  false;
        }
        return  false;*/
        Session session=DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Query<Travel> query = session.createQuery("from Travel d where d.id=:id ", Travel.class);
        query.setParameter("id",travel.getId());
        List<Travel> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList.size()>0;
    }

    public int save(Travel travel) throws SQLException {
       /* if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO travel (id_driver , id_passenger , origin , destination , price , date , hour , pay_type , travel_status) VALUES(%d,%d,'%s','%s',%2f,'%s','%s','%s','%s')"
                    , travel.getIdDriver(), travel.getIdPassenger(), travel.getOrigin(), travel.getDestination(), travel.getPrice(), travel.getDate(), travel.getHour(), travel.getPayType(), travel.getStatus());
            int i = statement.executeUpdate(sqlQuery);
            if (i != 0) {
                return searchId(travel);
            } else {
                return -1;
            }

        } else {
            return -1;
        }*/
        Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(travel);
        transaction.commit();
        session.close();
        return id;
    }
    public int deleteTravel(Travel travel) throws SQLException {
       /* if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("DELETE FROM travel WHERE  WHERE id_travel=%d", travel.getId());
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
        session.remove(travel);
        transaction.commit();
        session.close();
        return 1;

    }

    public int updateTravel(Travel travel) throws SQLException {
       /* if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("UPDATE travel SET travel_status = '%s' WHERE id_travel=%d", travel.getStatus(), travel.getId());
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
        session.update(travel);
        transaction.commit();
        session.close();
        return 1;

    }

    public List<String> getTravelInformation() throws SQLException {
        List<String> travelInfo = new ArrayList<>();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select * from travel inner join driver inner join passenger on travel.id_driver=driver.id_driver and travel.id_passenger=passenger.idpassenger and travel.travel_status='OnTravel'");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                Passenger passenger = new Passenger(resultSet.getString(23), resultSet.getString(24), resultSet.getString(25), resultSet.getBoolean(26)
                        , resultSet.getString(27), resultSet.getString(30), resultSet.getDouble(29), resultSet.getBoolean(28));
                Driver driver = new Driver(resultSet.getString(12), resultSet.getString(13), resultSet.getString(14), resultSet.getBoolean(16)
                        , resultSet.getString(17), resultSet.getString(15), resultSet.getDouble(19), resultSet.getString(18), resultSet.getString(20), resultSet.getInt(21));
                Travel travel = new Travel(resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5)
                        , resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10));
                travelInfo.add("Driver : "+ driver+"\n"+"passenger : "+passenger+"\n"+travel);
            }
            return travelInfo;
        } else {
            return  null;
        }
       /* Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Travel> criteria = builder.createQuery(Travel.class);
        Root<Travel> root =criteria.from(Travel.class);
        criteria.select(root).where(builder.gt(root.get("status"),))
        List<Travel> travelList = session.createQuery(criteria).getResultList();
        transaction.commit();
        session.close();
        return travelList;*/
    }

    public int searchId(Travel travel) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("SELECT id_travel FROM travel WHERE id_driver=%d and id_passenger=%d and date='%s'", travel.getIdDriver(), travel.getIdPassenger(), travel.getDate());
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return -1;
    }
    public List<Travel> getTravels() throws SQLException, ClassNotFoundException {
        List<Travel> travels ;
       /* if(getConnection()!=null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("SELECT * FROM travel");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                do {
                    Travel travel = new Travel(resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4),
                            resultSet.getString(5), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10));
                    travel.setId(resultSet.getInt(1));
                    travels.add(travel);
                } while (resultSet.next());
            }
        }
        return travels;*/
        DriverDataBase driverDataBase=new DriverDataBase();
        PassengerDataBase passengerDataBase=new PassengerDataBase();
         Session session = DataBaseAccess.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Travel> criteria = builder.createQuery(Travel.class);
        criteria.from(Travel.class);
         travels = session.createQuery(criteria).getResultList();
        transaction.commit();
        session.close();
       /* for (int i = 0; i <travels.size(); i++) {
            Travel travel=travels.get(i);
            travels.remove(travel);
           travel.setIdDriver(travel.getDriver().getId());
           travel.setIdPassenger(travel.getPassenger().getId());
            travels.add(travel);
        }*/
        for (Travel travel : travels) {
            travel.setIdDriver(travel.getDriver().getId());
            travel.setIdPassenger(travel.getPassenger().getId());
        }
        return travels;
    }

}
