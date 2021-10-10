package other_class;

public class Travel {
    private int id;
    private int idDriver;
    private int idPassenger;
    private String origin;
    private String destination;
    private String date;
    private  String hour;
    private  boolean payType;

    public Travel(int idDriver, int idPassenger, String origin, String destination, String date, String hour,boolean payType) {
        this.idDriver = idDriver;
        this.idPassenger = idPassenger;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.hour = hour;
        this.payType=payType;
    }

    public boolean isPayType() {
        return payType;
    }

    public void setPayType(boolean payType) {
        this.payType = payType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDriver() {
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
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", idDriver=" + idDriver +
                ", idPassenger=" + idPassenger +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", payType=" + payType +
                '}';
    }
}
