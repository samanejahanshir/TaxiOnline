package other_class;

public class Car {
    private String carTag;
    private String color;
    private  String model;
    private  String Type;

    public Car(String carTag, String color, String model, String type) {
        this.carTag = carTag;
        this.color = color;
        this.model = model;
        Type = type;
    }

    public String getCarTag() {
        return carTag;
    }

    public void setCarTag(String carTag) {
        this.carTag = carTag;
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
}
