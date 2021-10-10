package database;

import other_class.Travel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TravelDataBase extends DataBaseAccess {

    public TravelDataBase() throws ClassNotFoundException, SQLException {
        super();
    }

    public int save(Travel travel) throws SQLException {
        if (getConnection() != null) {
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
        }
    }

    public int updateTravel(Travel travel) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("UPDATE travel SET travel_status = '%s' WHERE id_travel=%d", travel.getStatus(), travel.getId());
            int i = statement.executeUpdate(sqlQuery);
            if (i != 0) {
                return i;
            } else {
                return -1;
            }

        }
        return -1;
    }

    public List<String> getTravelInformation(int id_driver, int id_passenger) throws SQLException {
        List<String> travelInfo = new ArrayList<>();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select * from travel inner join driver inner join passenger on travel.id_driver=%d and travel.id_passenger=%d and travel.travel_status=false", id_driver, id_passenger);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {

            }
        } else {
            //TODO
        }
        return null;
    }
    public  int searchId(Travel travel) throws SQLException {
        if(getConnection()!=null){
            Statement statement=getConnection().createStatement();
            String sqlQuery=String.format("SELECT id_travel FROM travel WHERE id_driver=%d and id_passenger=%d and date='%s'",travel.getIdDriver(),travel.getIdPassenger(),travel.getDate());
            ResultSet resultSet=statement.executeQuery(sqlQuery);
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        }
        return  -1;
    }
}
