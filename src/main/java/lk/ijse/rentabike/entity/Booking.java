package lk.ijse.rentabike.entity;

import java.sql.Date;

public class Booking {
    private String bookingId;
    private String chooseBike;
    private String pickUpLocation;
    private Date pickUpDate;
    private String pickUpTime;
    private String dropOffLocation;
    private Date dropOffDate;
    private String dropOffTime;
    private String bookingStatus;

    public Booking() {
    }

    public Booking(String bookingId, String chooseBike, String pickUpLocation, Date pickUpDate, String pickUpTime, String dropOffLocation, Date dropOffDate, String dropOffTime, String bookingStatus) {
        this.bookingId = bookingId;
        this.chooseBike = chooseBike;
        this.pickUpLocation = pickUpLocation;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.dropOffLocation = dropOffLocation;
        this.dropOffDate = dropOffDate;
        this.dropOffTime = dropOffTime;
        this.bookingStatus = bookingStatus;
    }

    public Booking(String bookingId) {
        this.bookingId=bookingId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getChooseBike() {
        return chooseBike;
    }

    public void setChooseBike(String chooseBike) {
        this.chooseBike = chooseBike;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public String getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(String dropOffTime) {
        this.dropOffTime = dropOffTime;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "bookingId='" + bookingId + '\'' +
                ", chooseBike='" + chooseBike + '\'' +
                ", pickUpLocation='" + pickUpLocation + '\'' +
                ", pickUpDate=" + pickUpDate +
                ", pickUpTime='" + pickUpTime + '\'' +
                ", dropOffLocation='" + dropOffLocation + '\'' +
                ", dropOffDate=" + dropOffDate +
                ", dropOffTime='" + dropOffTime + '\'' +
                ", bookingStatus='" + bookingStatus + '\'' +
                '}';
    }
}
