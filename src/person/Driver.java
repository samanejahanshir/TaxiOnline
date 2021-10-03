package person;

import java.util.Date;

public class Driver extends Person{
    private String carTag;

    public Driver(String firstName, String lastName, String nationalCode, boolean gender, String birthDate, String phoneNumber, double balance, String carTag) {
        super(firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance);
        this.carTag = carTag;
    }

    public String getCarTag() {
        return carTag;
    }

    public void setCarTag(String carTag) {
        this.carTag = carTag;
    }
}
