package person;

import java.util.Date;

public class Driver extends Person{
    private String carTag;
    private String origin;
    private boolean status;

    public Driver(String firstName, String lastName, String nationalCode, boolean gender, String birthDate, String phoneNumber, double balance, String carTag,String origin,boolean status) {
        super(firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance);
        this.carTag = carTag;
        this.origin=origin;
        this.status=status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
