package models;

import models.Driver;

import javax.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String vehicleTag;
    private String color;
    private  String model;
    private  String Type;
    @OneToOne(cascade = CascadeType.ALL)
    private Driver driver;

    public Vehicle(String carTag, String color, String model, String type) {
        this.vehicleTag = carTag;
        this.color = color;
        this.model = model;
        Type = type;
    }

    public Vehicle() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleTag() {
        return vehicleTag;
    }

    public void setVehicleTag(String vehicleTag) {
        this.vehicleTag = vehicleTag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleTag='" + vehicleTag + '\'' +
                ", color='" + color + '\'' +
                ", model='" + model + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
