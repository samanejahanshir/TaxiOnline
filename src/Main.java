import java.sql.SQLException;
import java.text.ParseException;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TaxiOnline taxiOnline = new TaxiOnline();
        taxiOnline.showMenu();
    }
}
