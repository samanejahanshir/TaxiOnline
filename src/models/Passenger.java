package models;

import javax.persistence.Entity;

@Entity
public class Passenger extends  Person{

    private  boolean attendanceStatus;

    public Passenger(String firstName, String lastName, String nationalCode, boolean gender, String birthDate, String phoneNumber, double balance, boolean attendanceStatus) {
        super(firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance);
        this.attendanceStatus = attendanceStatus;
    }

    public Passenger() {

    }

    public boolean isAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
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
                ", attendanceStatus='" + attendanceStatus + '\'' +
                '}';
    }


}
