package lk.ijse.rentabike.entity;

public class VehicleStatus {
    private String vehicleId;
    private String name;
    private String type;
    private double rentPrice;
    private String customerId;
    private String bookingdId;
    private String available;

    public VehicleStatus() {
    }

    public VehicleStatus(String vehicleId, String name, String type, double rentPrice, String customerId, String bookingdId, String available) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.type = type;
        this.rentPrice = rentPrice;
        this.customerId = customerId;
        this.bookingdId = bookingdId;
        this.available = available;
    }

    public VehicleStatus(String name) {
    }

    public VehicleStatus(String name, String available, String customerId, String bookingId) {
        this.name=name;
        this.available=available;
        this.customerId=customerId;
        this.bookingdId=bookingId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBookingdId() {
        return bookingdId;
    }

    public void setBookingdId(String bookingdId) {
        this.bookingdId = bookingdId;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "VehicleStatusDTO{" +
                "vehicleId='" + vehicleId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", rentPrice=" + rentPrice +
                ", customerId='" + customerId + '\'' +
                ", bookingdId='" + bookingdId + '\'' +
                ", available='" + available + '\'' +
                '}';
    }
}
