package lk.ijse.rentabike.entity;

public class Vehicle {
    String vehicleId;
    String vehicleName;
    String type;
    Double rent;
    String milage;
    String firstAidKit;
    String transmission;
    String roadAssistance;
    String available;

    public Vehicle() {
    }

    public Vehicle(String vehicleId, String vehicleName, String type, Double rent, String milage, String firstAidKit, String transmission, String roadAssistance, String available) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.type = type;
        this.rent = rent;
        this.milage = milage;
        this.firstAidKit = firstAidKit;
        this.transmission = transmission;
        this.roadAssistance = roadAssistance;
        this.available = available;
    }

    public Vehicle(String vehicleId) {
        this.vehicleId=vehicleId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getMilage() {
        return milage;
    }

    public void setMilage(String milage) {
        this.milage = milage;
    }

    public String getFirstAidKit() {
        return firstAidKit;
    }

    public void setFirstAidKit(String firstAidKit) {
        this.firstAidKit = firstAidKit;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getRoadAssistance() {
        return roadAssistance;
    }

    public void setRoadAssistance(String roadAssistance) {
        this.roadAssistance = roadAssistance;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", type='" + type + '\'' +
                ", rent=" + rent +
                ", milage='" + milage + '\'' +
                ", firstAidKit='" + firstAidKit + '\'' +
                ", transmission='" + transmission + '\'' +
                ", roadAssistance='" + roadAssistance + '\'' +
                ", available='" + available + '\'' +
                '}';
    }
}
