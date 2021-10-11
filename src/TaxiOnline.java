import database.DriverDataBase;
import database.PassengerDataBase;
import database.TravelDataBase;
import exception.*;
import other_class.Travel;
import other_class.Vehicle;
import person.Driver;
import person.Passenger;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.*;

public class TaxiOnline {
    List<Driver> drivers = new ArrayList<>();
    List<Passenger> passengers = new ArrayList<>();
    DriverDataBase driverDataBase = new DriverDataBase();
    PassengerDataBase passengerDataBase = new PassengerDataBase();
    TravelDataBase travelDataBase = new TravelDataBase();
    List<Travel> travels = new ArrayList<>();

    public TaxiOnline() throws SQLException, ClassNotFoundException {
        drivers = driverDataBase.getListDrivers();
        passengers = passengerDataBase.getListPassengers();
        travels=travelDataBase.getTravels();
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
                    "\n7.Show Ongoing Travels" +
                    "\n8.exit\n ------------------------------");
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
                    showOngoingTravels();
                    return false;
                case 8:
                    return true;

                default:
                    System.out.println("enter number between 1 and 8 please !");
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
                    System.out.println("this passenger with this user name was exist ! ");
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

    public void driverSignUpOrLogIn() throws SQLException {
        boolean noExit = true;
        System.out.println("enter user name :");
        Scanner scanner = new Scanner(System.in);
        String nationalCode = scanner.next();
        drivers = driverDataBase.getListDrivers();
        Driver driver = searchDriverWithNCode(nationalCode);
        try {
            if (driver != null) {
                travels = travelDataBase.getTravels();
                Travel travel = searchTravelForDriver(nationalCode);
                if (travel == null) {
                    System.out.println("you are waiting for start  travel ");
                    System.out.println("1.exit");
                    int selectItem = scanner.nextInt();
                    if (selectItem == 1) {
                        noExit = false;
                    }
                } else {
                    Passenger passenger = searchPassengerWithId(travel.getIdPassenger());
                    System.out.println(travel.toString() + " passenger name : " + passenger.getFirstName() + "  passenger family : " + passenger.getLastName());
                  if(!driver.getStatus()) {
                      while (noExit) {
                          System.out.println("1.confirmation travel \n2.cancel travel\n3.exit ");
                          try {
                              int selectItem = scanner.nextInt();
                              switch (selectItem) {
                                  case 1:
                                      travel.setStatus(StatusTravel.ONTRAVEL.getName());
                                      driver.setStatus(true);
                                      driverDataBase.updateDriverStatus(driver);
                                      if (!travelDataBase.findTravel(travel)) {
                                          int id = travelDataBase.save(travel);
                                          travel.setId(id);
                                      } else {
                                          travelDataBase.updateTravel(travel);
                                      }
                                      int indexTravel = setIdFromListTravel(travel);
                                      System.out.println("update travel list by index " + indexTravel);
                                      passenger.setAttendanceStatus(true);
                                      int index = passengers.indexOf(passenger);
                                      passengers.get(index).setAttendanceStatus(true);
                                      passengerDataBase.updatePassengerStatus(passenger);
                                      noExit = false;
                                      break;
                                  case 2:
                                      passenger.setAttendanceStatus(false);
                                      int indexP = passengers.indexOf(passenger);
                                      passengers.get(indexP).setAttendanceStatus(false);
                                      travels.remove(travel);
                                      travelDataBase.deleteTravel(travel);
                                      noExit = false;
                                      break;
                                  case 3:
                                      noExit = false;
                                      break;
                                  default:
                                      System.out.println("enter 1 or 2 or 3");
                              }
                          } catch (NumberException e) {
                              System.out.println(e.getMessage());
                          }
                      }
                  }else {
                              int end = showTravelAndManageIt(travel, driver, passenger);
                              driver.setOrigin(travel.getDestination());
                              driver.setStatus(false);
                              if (end == 2) {
                                  if (driverDataBase.updateDriver(driver) != -1) {
                                      System.out.println("update origin driver was successfully");
                                  } else {
                                      System.out.println("update origin driver was failed");

                                  }
                                  passenger.setAttendanceStatus(false);
                                  passengerDataBase.updatePassengerStatus(passenger);
                                  driverDataBase.updateDriverStatus(driver);
                              }
                  }

                }
            } else {
                while (noExit) {
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
                            noExit = false;
                            break;
                        default:
                            System.out.println("enter 1 or 2 ! ");
                    }
                }
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println(e.getMessage());

        }

    }

    public void passengerSignUpOrLogIn() throws SQLException {
        boolean noExit = true;
        System.out.println("enter user name :");
        Scanner scanner = new Scanner(System.in);
        String nationalCode = scanner.next();
        passengers = passengerDataBase.getListPassengers();
        Passenger passenger = searchPassengerWithNCode(nationalCode);
        try {
            if (passenger != null) {
                System.out.println(passenger.toString());
                if (!passenger.isAttendanceStatus()) {

                    while (noExit) {

                        System.out.println("1.TravelRequest (pay by catch)\n2.TravelRequest ( pay by account balance)\n3.Increase account balance\n4.exit");

                        int selectItem = scanner.nextInt();
                        switch (selectItem) {
                            case 1:
                                travelRequest(passenger, PayType.BYCATCH.getName());
                                break;
                            case 2:
                                travelRequest(passenger, PayType.BYACCOUNT.getName());
                                break;
                            case 3:
                                if (incrementBalancePassenger(nationalCode)) {
                                    System.out.println("Increment balance was successfully");
                                } else {
                                    System.out.println("Increment balance was failed !");

                                }
                                continue;
                            case 4:
                                noExit = false;
                                break;
                            default:
                                System.out.println("enter between 1 - 4 ! ");
                        }
                    }
                } else if (passenger.isAttendanceStatus()) {
                    System.out.println("you are traveling ... ");
                    Travel travel=searchTravelForPassenger(passenger.getId());
                    if(travel!=null){
                        System.out.println("******************** ");
                        System.out.println("your request accept by : ");
                        Driver driver=searchDriverWithId(travel.getIdDriver());
                        System.out.println(driver.getFirstName()+" "+driver.getLastName());
                        System.out.println(driverDataBase.getVehicle(driver.getCarTag()));
                        System.out.println("******************** ");

                    }

                }

            } else {
                while (noExit) {
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
                            noExit = false;
                            break;
                        default:
                            System.out.println("enter 1 or 2 ! ");
                    }
                }
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println("enter number please ! ");
        }


    }

    public void showListOfDrivers() throws SQLException {
        drivers = driverDataBase.getListDrivers();
        for (Driver driver : drivers) {
            System.out.println(driver);

        }
    }

    public void showListOfPassengers() throws SQLException {
        passengers = passengerDataBase.getListPassengers();
        for (Passenger passenger : passengers) {
            System.out.println(passenger);

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
        System.out.println("origin: like this ==> 1000,1000 : ");
        String origin = scanner.next();
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
                    Driver driver = new Driver(firstName, lastName, nationalCode, man, myDate.toString(), Long.parseLong(mobile) + "", 0, carTag, origin, false);
                    if (driverDataBase.save(driver) != 0 && driverDataBase.saveVehicle(vehicle) != 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (NumberException | StringException | DateException | SQLException | InputMismatchException | TagFormatException | OriginFormatExcp e) {
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
            if (passengerDataBase.changeBalance(nationalCode, amount + passengerDataBase.showBalance(nationalCode))) {
                return true;
            } else {
                return false;
            }


        } catch (NumberFormatException | SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    public Passenger searchPassengerWithNCode(String nationalCode) {
        for (Passenger passenger : passengers) {
            if (passenger.getNationalCode().equals(nationalCode)) {
                return passenger;
            }
        }
        return null;
    }

    public Passenger searchPassengerWithId(int id) {
        for (Passenger passenger : passengers) {
            if (passenger.getId() == id) {
                return passenger;
            }
        }
        return null;
    }


    public Driver searchDriverWithNCode(String nationalCode) {
        for (Driver driver : drivers) {
            if (driver.getNationalCode().equals(nationalCode)) {
                return driver;
            }
        }
        return null;
    }

    public Driver searchDriverWithId(int id) {
        for (Driver driver : drivers) {
            if (driver.getId() == id) {
                return driver;
            }
        }
        return null;
    }

    public int searchDriverId(String nationalCode) {
        for (Driver driver : drivers) {
            if (driver.getNationalCode().equals(nationalCode)) {
                return driver.getId();
            }
        }
        return -1;
    }

    public int setIdFromListTravel(Travel travel) {

        for (Travel travelTemp : travels) {
            if (travelTemp.getIdDriver() == travel.getIdDriver() && travelTemp.getIdPassenger() == travel.getIdPassenger() && travelTemp.getDate().equals(travel.getDate())) {
                travelTemp.setId(travel.getId());
                return travelTemp.getId();
            }
        }
        return -1;
    }

    public void travelRequest(Passenger passenger, String payType) {
        System.out.println("origin : like 1000,1000 : ");
        Scanner scanner = new Scanner(System.in);
        String origin = scanner.next();
        System.out.println("destination: like 1000,1000 : ");
        String destination = scanner.next();
        try {
            if (CheckValidation.checkOriginFormat(origin) && CheckValidation.checkOriginFormat(destination)) {
                Driver driver = searchDriverForTravel(origin);
                System.out.println("year :");
                String year = scanner.next();
                System.out.println("month :");
                String month = scanner.next();
                System.out.println("day :");
                String day = scanner.next();
                MyDate myDate = new MyDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                LocalTime time = LocalTime.now();
                if (driver != null && myDate.isValidDate(myDate.getYear(), myDate.getMonth(), myDate.getDay())) {
                    Travel travel = new Travel(driver.getId(), passenger.getId(), origin, destination, myDate.toString(), time + "", payType, StatusTravel.WAITING.getName());
                    if (travel.getPayType().equals(PayType.BYACCOUNT.getName())) {
                        if (travel.getPrice() > passenger.getBalance()) {
                            System.out.println("your balance is not enough !");

                        } else {
                            travelDataBase.save(travel);

                        }
                    } else {
                        travelDataBase.save(travel);
                    }

                }
            }
        } catch (OriginFormatExcp | SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }


    public Driver searchDriverForTravel(String origin) throws SQLException {
        drivers = driverDataBase.getListDrivers();
        String[] originElement = origin.split(",");
        Driver driverSelect = null;
        String[] destinationD1 = drivers.get(0).getOrigin().split(",");
        double distance = Math.pow(Integer.parseInt(destinationD1[0]) - Integer.parseInt(originElement[0]), 2)
                + Math.pow(Integer.parseInt(destinationD1[1]) - Integer.parseInt(originElement[1]), 2);
        for (Driver driver : drivers) {
            String[] destinationElement = driver.getOrigin().split(",");
            double tempDistance = Math.pow(Integer.parseInt(destinationElement[0]) - Integer.parseInt(originElement[0]), 2)
                    + Math.pow(Integer.parseInt(destinationElement[1]) - Integer.parseInt(originElement[1]), 2);
            if (distance >= tempDistance) {
                distance = tempDistance;
                driverSelect = driver;
            }

        }

        return driverSelect;

    }

    public Travel searchTravelForDriver(String nationalCode) {
        int idDriver = searchDriverId(nationalCode);
        for (Travel travel : travels) {
            if (travel.getIdDriver() == idDriver && (travel.getStatus().equals(StatusTravel.WAITING.getName()) || travel.getStatus().equals(StatusTravel.ONTRAVEL.getName()))) {
                return travel;
            }
        }
        return null;
    }

    public Travel searchTravelForPassenger(int id) {

        for (Travel travel : travels) {
            if (travel.getIdPassenger() == id && (travel.getStatus().equals(StatusTravel.ONTRAVEL.getName()))) {
                return travel;
            }
        }
        return null;
    }

    public int showTravelAndManageIt(Travel travel, Driver driver, Passenger passenger) {
        boolean noExit = true;
        Scanner scanner = new Scanner(System.in);
        try {
            if (travel.getPayType().equals(PayType.BYCATCH.getName())) {
                while (noExit) {
                    System.out.println("1.Confirm catch receipt \n2.Travel finished\n3.Exit");
                    int selectItem = scanner.nextInt();
                    switch (selectItem) {
                        case 1:
                            System.out.println("get catch from passenger");
                            continue;
                        case 2:
                            travel.setStatus(StatusTravel.ENDTRAVEL.getName());
                            travelDataBase.updateTravel(travel);
                            noExit = false;
                            return 2;
                        case 3:
                            noExit = false;
                            break;

                    }

                }
            } else if (travel.getPayType().equals(PayType.BYACCOUNT.getName())) {
                System.out.println("get not catch ... it is online payment \n2.Travel finished\n3.Exit");
                int selectItem = scanner.nextInt();
                switch (selectItem) {
                    case 2:
                        travel.setStatus(StatusTravel.ENDTRAVEL.getName());
                        if (travelDataBase.updateTravel(travel) != -1) {
                            System.out.println("update travel table was successfully");
                            passengerDataBase.changeBalance(passenger.getNationalCode(), passenger.getBalance() - travel.getPrice());
                            driverDataBase.changeBalance(driver.getNationalCode(), driver.getBalance() + travel.getPrice());

                        }
                        noExit = false;
                        return  2;
                    case 3:
                        noExit = false;
                        break;

                }
            }
        } catch (NumberException | SQLException e) {
            System.out.println(e.getMessage());
        }
    return  -1;
    }

    public void showOngoingTravels() throws SQLException {
        List<String> listInfoTravel = travelDataBase.getTravelInformation();
        if(listInfoTravel.size()==0){
            System.out.println("there aren't any travel  ");
        }
        for (String information : listInfoTravel) {
            System.out.println(information);

        }

    }
}
