public enum StatusTravel {
    WAITING("Waiting"),
    ONTRAVEL("OnTravel"),
    ENDTRAVEL("EndTravel");
    private String name;

    StatusTravel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
