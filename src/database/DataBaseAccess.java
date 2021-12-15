package database;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseAccess {
    private Connection connection=null;
    private static SessionFactory sessionFactory=null;

    public DataBaseAccess() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/taxi_online_database", "root", "1234567890");
    }
public  static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            sessionFactory=new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
        }
        return  sessionFactory;
}
    public Connection getConnection() {
        return connection;
    }
}
