package models;

import javax.persistence.*;

@Entity

public class Driver extends Person{
    private String carTag;
    private String origin;
    private int status;
    @OneToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

    public Driver(String firstName, String lastName, String nationalCode, boolean gender, String birthDate, String phoneNumber, double balance, String carTag,String origin,int status) {
        super(firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance);
        this.carTag = carTag;
        this.origin=origin;
        this.status=status;
    }

    public Driver() {

    }

    public int isStatus() {
        return status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCarTag() {
        return carTag;
    }

    public void setCarTag(String carTag) {
        this.carTag = carTag;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        String gender;
        if(isGender()==true){
            gender="female";
        }else {
            gender="male";
        }
        return "Person{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", nationalCode='" + getNationalCode() + '\'' +
                ", gender='" + gender +'\''+
                ", birthDate='" + getBirthDate() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", balance=" + getBalance() +
                ", car_tag='" + carTag + '\'' +
                ", origin='" + origin+ '\'' +
                ", status='" + status+ '\'' +

                '}';
    }
}
