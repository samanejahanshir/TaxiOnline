package view;

import services.TaxiOnline;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TaxiOnline taxiOnline = new TaxiOnline();
        taxiOnline.showMenu();
    }
}
