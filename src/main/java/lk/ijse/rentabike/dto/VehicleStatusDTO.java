package lk.ijse.rentabike.dto;

import java.io.Serializable;

public class VehicleStatusDTO implements Serializable {
    private String vehicleId;
    private String name;
    private String type;
    private double rent_price;
    private String customer_id;
    private String booking_id;
    private String available;

    public VehicleStatusDTO() {
    }

    public VehicleStatusDTO(String vehicleId, String name, String type, double rent_price, String customer_id, String booking_id, String available) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.type = type;
        this.rent_price = rent_price;
        this.customer_id = customer_id;
        this.booking_id = booking_id;
        this.available = available;
    }

    public VehicleStatusDTO(String name) {
        this.name=name;
    }

    public VehicleStatusDTO(String chooseBike, String booked, String customerId, String bookingId) {
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

    public double getRent_price() {
        return rent_price;
    }

    public void setRent_price(double rent_price) {
        this.rent_price = rent_price;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
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
                ", rent_price=" + rent_price +
                ", customer_id='" + customer_id + '\'' +
                ", booking_id='" + booking_id + '\'' +
                ", available='" + available + '\'' +
                '}';
    }
}
