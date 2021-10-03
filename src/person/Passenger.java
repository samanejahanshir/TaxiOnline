package person;

import java.util.Date;

public class Passenger extends  Person{
    private  boolean attendanceStatus;

    public Passenger(String firstName, String lastName, String nationalCode, boolean gender, String birthDate, String phoneNumber, double balance, boolean attendanceStatus) {
        super(firstName, lastName, nationalCode, gender, birthDate, phoneNumber, balance);
        this.attendanceStatus = attendanceStatus;
    }

    public boolean isAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
