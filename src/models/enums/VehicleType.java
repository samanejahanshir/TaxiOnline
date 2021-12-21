package models.enums;

public enum VehicleType {
    CAR("car"),
    VAN("van"),
    MOTORCYCLE("motorCycle"),
    PICKUP("pickUp");
    private String name;

    VehicleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
