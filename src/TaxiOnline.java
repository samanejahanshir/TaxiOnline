import person.Driver;
import person.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaxiOnline {
    List<Driver> drivers = new ArrayList<>();
    List<Passenger> passengers = new ArrayList<>();

    public void showMenu() {
        boolean exit=false;
        while (!exit) {
            System.out.println("---------- welcome to taxi online ----------");
            System.out.println("1.Add a group of drivers" +
                    "\n2.Add a group of passengers" +
                    "\n3.Driver signup or login" +
                    "\n4.Passenger signup or login" +
                    "\n5.show ongoing travels" +
                    "\n6.show a list of drivers" +
                    "\n7.show a list of passengers" +
                    "\n8.exit\n ------------------------------");
            exit= selectMenuItem();
        }
    }

    public boolean selectMenuItem() {
        Scanner scanner = new Scanner(System.in);
        String selectItem = scanner.next();
        try {
switch (Integer.parseInt(selectItem)){
    case 1:
        if(addDrivers()){
            System.out.println("Add Driver was Successfully");
        }else {
            System.out.println("Add Driver was failed");
        }
        return false;
    case 2:
        if(addPassengers()){
            System.out.println("Add Passenger was Successfully");
        }else {
            System.out.println("Add Passenger was failed");
        }
        return false;
    case 3:
        driverSignUpOrLogIn();
        return false;
    case 4:
        passengerSignUpOrLogIn();
        return  false;
    case 5:
        showOnGoingTravels();
        return false;
    case 6:
        showListOfDrivers();
        return  false;
    case 7:
        showListOfPassengers();
        return  false;
    case 8:
        return true;
    default:
        System.out.println("enter number between 1 and 8 please !");
        return false;
}

        } catch (NumberFormatException e) {
            System.out.println("enter number please !");
            e.getStackTrace();
            return false;
        }
    }
    public boolean addDrivers(){
        //TODO
        return true;        //TODO

    }
    public boolean addPassengers(){
        //TODO
        return true;        //TODO


    }
    public boolean driverSignUpOrLogIn(){
        //TODO
        return true;        //TODO


    }
    public boolean passengerSignUpOrLogIn(){
        //TODO
        return true;        //TODO


    }

    public void showOnGoingTravels(){
        //TODO
    }
    public void showListOfDrivers(){
        //TODO
    }
    public void showListOfPassengers(){
        //TODO
    }


}
