import database.DriverDataBase;
import database.PassengerDataBase;
import exception.*;
import other_class.Travel;
import other_class.Vehicle;
import person.Driver;
import person.Passenger;
import sun.util.resources.cldr.CalendarData;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class TaxiOnline {
    List<Driver> drivers = new ArrayList<>();
    List<Passenger> passengers = new ArrayList<>();
    DriverDataBase driverDataBase = new DriverDataBase();
    PassengerDataBase passengerDataBase = new PassengerDataBase();
    List<Travel> travels=new ArrayList<>();

    public TaxiOnline() throws SQLException, ClassNotFoundException {
        drivers = driverDataBase.showListDrivers();
        passengers = passengerDataBase.showListPassengers();
    }

    public void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("---------- welcome to taxi online ----------");
            System.out.println("1.Add a group of drivers" +
                    "\n2.Add a group of passengers" +
                    "\n3.Driver signup or login" +
                    "\n4.Passenger signup or login" +
                    "\n5.show a list of drivers" +
                    "\n6.show a list of passengers" +
                    "\n7.exit\n ------------------------------");
            exit = selectMenuItem();
        }
    }

    public boolean selectMenuItem() {
        Scanner scanner = new Scanner(System.in);
        String selectItem = scanner.next();
        try {
            switch (Integer.parseInt(selectItem)) {
                case 1:
                    if (addDrivers()) {
                        System.out.println("Add Driver was Successfully");
                    } else {
                        System.out.println("Add Driver was failed");
                    }
                    return false;
                case 2:
                    if (addPassengers()) {
                        System.out.println("Add Passenger was Successfully");
                    } else {
                        System.out.println("Add Passenger was failed");
                    }
                    return false;
                case 3:
                    driverSignUpOrLogIn();
                    return false;
                case 4:
                    passengerSignUpOrLogIn();
                    return false;
                case 5:
                    showListOfDrivers();
                    return false;
                case 6:
                    showListOfPassengers();
                    return false;
                case 7:
                    return true;

                default:
                    System.out.println("enter number between 1 and 7 please !");
                    return false;
            }

        } catch (NumberFormatException | SQLException | InputMismatchException e) {
            System.out.println("enter number please !");
            e.getStackTrace();
            return false;
        }
    }

    public boolean addDrivers() {
        boolean add = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter number of driver to add : ");
        try {
            int numberOfDriver = scanner.nextInt();
            for (int i = 0; i < numberOfDriver; i++) {
                System.out.println("enter user name " + (i + 1) + " :");
                String nationalCode = scanner.next();

                if (driverDataBase.searchDriver(Long.parseLong(nationalCode) + "") != -1) {
                    System.out.println("this driver with this user name was exist ! ");
                } else {
                    add = registerDriver(nationalCode);
                }
                System.out.println("--------------------");

            }
        } catch (NumberFormatException | SQLException | StringException | NumberException | DateException | InputMismatchException e) {
            System.out.println(e.getMessage() + " " + e.getStackTrace());
            return false;
        }
        return add;

    }

    public boolean addPassengers() {
        boolean add = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter number of passenger to add : ");
        try {
            int numberOfDriver = scanner.nextInt();
            for (int i = 0; i < numberOfDriver; i++) {
                System.out.println("enter user name " + (i + 1) + " :");
                String nationalCode = scanner.next();
                if (passengerDataBase.searchPassenger(Long.parseLong(nationalCode) + "") != -1) {
                    System.out.println("this driver with this user name was exist ! ");
                } else {
                    add = registerPassenger(nationalCode);
                }
                System.out.println("--------------------");

            }
        } catch (NumberFormatException | SQLException | StringException | DateException | NumberException | InputMismatchException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return add;


    }

    public int driverSignUpOrLogIn() {
        System.out.println("enter user name :");
        Scanner scanner = new Scanner(System.in);
        String nationalCode = scanner.next();
        try {
            if (driverDataBase.searchDriver(nationalCode) != -1) {
                while (true) {
                    System.out.println("1.show balance\n2.exit");
                    int selectItem = scanner.nextInt();
                    switch (selectItem) {
                        case 1:

                            System.out.println("balance : " + driverDataBase.showBalance(nationalCode));
                        case 2:
                            return 2;
                        default:
                            System.out.println("enter 1 or 2 ! ");
                    }
                }

            } else {
                while (true) {
                    System.out.println("1.Register\n2.exit");
                    int selectItem = scanner.nextInt();
                    switch (selectItem) {
                        case 1:
                            if (registerDriver(nationalCode)) {
                                System.out.println("Register was successfully");
                            } else {
                                System.out.println("Register was failed !");
                            }
                            continue;
                        case 2:
                            return 2;
                        default:
                            System.out.println("enter 1 or 2 ! ");
                    }
                }
            }
        } catch (NumberFormatException | SQLException | InputMismatchException e) {
            System.out.println(e.getMessage());

        }
        return 0;
    }

    public int passengerSignUpOrLogIn() throws SQLException {
        System.out.println("enter user name :");
        Scanner scanner = new Scanner(System.in);
        String nationalCode = scanner.next();
        passengers = passengerDataBase.showListPassengers();
        Passenger passenger = searchPassenger(nationalCode);
        try {
            if (passenger != null && passenger.isAttendanceStatus() == false) {

                System.out.println(passenger.toString());
                while (passenger.isAttendanceStatus() == false) {
                    System.out.println("1.TravelRequest (pay by catch)\n2.TravelRequest ( pay by account balance)\n3.Increase account balance\n4.exit");

                    int selectItem = scanner.nextInt();
                    switch (selectItem) {
                        case 1:
                            travelRequest(passenger,PayType.BYCATCH.getName());
                            System.out.println(travels.get(0));
                        case 2:
                            travelRequest(passenger,PayType.BYACCOUNT.getName());

                        case 3:
                            if (incrementBalancePassenger(nationalCode)) {
                                System.out.println("Increment balance was successfully");
                            } else {
                                System.out.println("Increment balance was failed !");

                            }
                        case 4:
                            return 4;
                        default:
                            System.out.println("enter between 1 - 4 ! ");
                    }
                }

            } else {
                while (true) {
                    System.out.println("1.Register\n2.exit");
                    int selectItem = scanner.nextInt();
                    switch (selectItem) {
                        case 1:
                            if (registerPassenger(nationalCode)) {
                                System.out.println("Register was successfully");
                            } else {
                                System.out.println("Register was failed !");
                            }
                            continue;
                        case 2:
                            return 2;
                        default:
                            System.out.println("enter 1 or 2 ! ");
                    }
                }
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println("enter number please ! ");
        }
        return 0;


    }

    public void showListOfDrivers() throws SQLException {
        drivers = driverDataBase.showListDrivers();
        for (int i = 0; i < drivers.size(); i++) {
            System.out.println(drivers.get(i));

        }
    }

    public void showListOfPassengers() throws SQLException {
        passengers = passengerDataBase.showListPassengers();
        for (int i = 0; i < passengers.size(); i++) {
            System.out.println(passengers.get(i));

        }
    }

    public boolean registerDriver(String nationalCode) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("first name :");
        String firstName = scanner.next();
        System.out.println("last name :");
        String lastName = scanner.next();
        System.out.println("mobile number :");
        String mobile = scanner.next();
        System.out.println("gender 0 for man and 1 for woman :");
        int gender = scanner.nextInt();
        boolean man = false;
        if (gender == 1) {
            man = true;
        } else if (gender != 1 && gender != 0) {
            System.out.println("enter number 0 or 1 !! ");
            return false;
        }
        System.out.println("car tag :");
        String carTag = scanner.next();
        System.out.println("car color:");
        String color = scanner.next();
        System.out.println("car type: v => van  c => car  m => motorcycle  p => pickUp");
        String type = scanner.next();
        System.out.println("origin: like this ==> 1000,1000 : ");
        String origin = scanner.next();
        switch (type) {
            case "v":
                type = VehicleType.VAN.getName();
                break;
            case "c":
                type = VehicleType.CAR.getName();
                break;
            case "p":
                type = VehicleType.PICKUP.getName();
                break;
            case "m":
                type = VehicleType.MOTORCYCLE.getName();
                break;

            default:
                System.out.println("invalid type of vehicle");
        }
        System.out.println("car model :");
        String model = scanner.next();
        System.out.println("birth date");
        System.out.println("year :");
        String year = scanner.next();
        System.out.println("month :");
        String month = scanner.next();
        System.out.println("day :");
        String day = scanner.next();
        MyDate myDate = new MyDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        try {
            if (myDate.isValidDate(myDate.getYear(), myDate.getMonth(), myDate.getDay())) {
                if (CheckValidation.checkString(firstName) && CheckValidation.checkString(lastName) && CheckValidation.checkInt(mobile) && CheckValidation.isValidateTagVehicle(carTag) && CheckValidation.checkOriginFormat(origin)) {
                    Vehicle vehicle = new Vehicle(carTag, color, model, type);
                    Driver driver = new Driver(firstName, lastName, nationalCode, man, myDate.toString(), Long.parseLong(mobile) + "", 0, carTag, origin);
                    if (driverDataBase.save(driver) != 0 && driverDataBase.saveVehicle(vehicle) != 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (NumberException | StringException | DateException | SQLException | InputMismatchException | TagFormatException  | OriginFormatExcp e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean registerPassenger(String nationalCode) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("first name :");
        String firstName = scanner.next();
        System.out.println("last name :");
        String lastName = scanner.next();
        System.out.println("mobile number :");
        String mobile = scanner.next();
        System.out.println("gender 0 for man and 1 for woman :");
        int gender = scanner.nextInt();
        boolean man = false;
        if (gender == 1) {
            man = true;
        } else if (gender != 1 && gender != 0) {
            System.out.println("enter number 0 or 1 !! ");
            return false;
        }
        System.out.println("birth date");
        System.out.println("year :");
        String year = scanner.next();
        System.out.println("month :");
        String month = scanner.next();
        System.out.println("day :");
        String day = scanner.next();
        MyDate myDate = new MyDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        try {
            if (myDate.isValidDate(myDate.getYear(), myDate.getMonth(), myDate.getDay())) {
                if (CheckValidation.checkString(firstName) && CheckValidation.checkString(lastName) && CheckValidation.checkInt(mobile)) {

                    Passenger passenger = new Passenger(firstName, lastName, nationalCode, man, myDate.toString(), Long.parseLong(mobile) + "", 0, false);
                    if (passengerDataBase.save(passenger) != 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (SQLException | StringException | NumberException | DateException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean incrementBalancePassenger(String nationalCode) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter amount of increment balance :");
        try {
            double amount = scanner.nextDouble();
            if (passengerDataBase.IncrementBalance(nationalCode, amount + passengerDataBase.showBalance(nationalCode))) {
                return true;
            } else {
                return false;
            }


        } catch (NumberFormatException | SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    public Passenger searchPassenger(String nationalCode) {
        for (Passenger passenger : passengers) {
            if (passenger.getNationalCode().equals(nationalCode)) {
                return passenger;
            }
        }
        return null;
    }

    public void travelRequest(Passenger passenger,String payType) {
        System.out.println("origin : like 1000,1000 : ");
        Scanner scanner = new Scanner(System.in);
        String origin = scanner.next();
        System.out.println("destination: like 1000,1000 : ");
        String destination = scanner.next();
        try {
            if (CheckValidation.checkOriginFormat(origin) && CheckValidation.checkOriginFormat(destination)) {
                Driver driver = searchDriverForTravel(origin);
                System.out.println("year :");
                String year=scanner.next();
                System.out.println("month :");
                String month=scanner.next();
                System.out.println("day :");
                String day=scanner.next();
                MyDate myDate=new MyDate(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
                LocalTime time=LocalTime.now();
                if(driver!=null && myDate.isValidDate(myDate.getYear(),myDate.getMonth(),myDate.getDay())){
                    travels.add(new Travel(driver.getId(),passenger.getId(),origin,destination,myDate.toString(),time+"",payType,false));
                }
            }
        }catch (OriginFormatExcp | SQLException exp){
            System.out.println(exp.getMessage());
        }
    }


    public Driver searchDriverForTravel(String origin) throws SQLException {
        drivers = driverDataBase.showListDrivers();
        String[] originElement = origin.split(",");
        Driver driverSelect=null;
        String[] destinationD1 = drivers.get(0).getOrigin().split(",");
        double distance = Math.pow(Integer.parseInt(destinationD1[0]) - Integer.parseInt(originElement[0]), 2)
                + Math.pow(Integer.parseInt(destinationD1[1]) - Integer.parseInt(originElement[1]), 2);
        for (Driver driver : drivers) {
            String[] destinationElement = driver.getOrigin().split(",");
            double tempDistance = Math.pow(Integer.parseInt(destinationElement[0]) - Integer.parseInt(originElement[0]), 2)
                    + Math.pow(Integer.parseInt(destinationElement[1]) - Integer.parseInt(originElement[1]), 2);
            if(distance>=tempDistance){
                distance=tempDistance;
                driverSelect=driver;
            }

        }

        return  driverSelect;

    }
}
