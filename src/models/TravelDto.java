package models;

import models.enums.PayType;

public class TravelDto {
    private String origin;
    private String destination;
    private String driver;
    private  String passenger;
    private PayType payType;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        return "TravelDto{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", driver='" + driver + '\'' +
                ", passenger='" + passenger + '\'' +
                ", payType='" + payType + '\'' +
                '}';
    }
}
