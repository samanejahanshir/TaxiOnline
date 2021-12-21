package models;

import models.enums.PayType;
import models.enums.StatusTravel;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /* @Transient
     private int idDriver;
     @Transient
     private int idPassenger;*/
    private String origin;
    private String destination;
    private double price;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date date;
    @Temporal(TemporalType.TIME)
    @CreationTimestamp
    private Date hour;
    @Enumerated(EnumType.STRING)
    private PayType payType;
    @Enumerated(EnumType.STRING)
    private StatusTravel status;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Driver driver;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Passenger passenger;

    public Travel(String origin, String destination, PayType payType, StatusTravel status) {
      /*  this.idDriver = idDriver;
        this.idPassenger = idPassenger;*/
        this.origin = origin;
        this.destination = destination;
        calculatePrice();
        this.hour = hour;
        this.payType = payType;
        this.status = status;
    }

    public Travel() {

    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public PayType getPayType() {
        return payType;
    }

    public StatusTravel getStatus() {
        return status;
    }

    public void setStatus(StatusTravel status) {
        this.status = status;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  /*  public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    public int getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(int idPassenger) {
        this.idPassenger = idPassenger;
    }*/

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public void calculatePrice() {
        String[] originElement = this.origin.split(",");
        String[] destinationElement = this.destination.split(",");
        double distance = Math.pow(Integer.parseInt(destinationElement[0]) - Integer.parseInt(originElement[0]), 2)
                + Math.pow(Integer.parseInt(destinationElement[1]) - Integer.parseInt(originElement[1]), 2);

        this.price = (distance / 1000000) * 1000;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", payType=" + payType +
                ", status=" + status +
                '}';
    }
}
