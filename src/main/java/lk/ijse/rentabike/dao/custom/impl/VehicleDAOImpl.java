package lk.ijse.rentabike.dao.custom.impl;

import lk.ijse.rentabike.dao.custom.VehicleDAO;
import lk.ijse.rentabike.dao.custom.impl.util.SQLUtil;
import lk.ijse.rentabike.entity.Vehicle;
import lk.ijse.rentabike.entity.VehicleStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public boolean save(Vehicle entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Vehicle (vehicleId, name, type, rent_price, mileage, first_aid_kit, transmission, roadside_assistance, available) VALUES (?,?,?,?,?,?,?,?,?)",
                entity.getVehicleId(), entity.getVehicleName(), entity.getType(), entity.getRent(), entity.getMilage(), entity.getFirstAidKit(),
                entity.getTransmission(), entity.getRoadAssistance(), entity.getAvailable());
    }

    @Override
    public ArrayList<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Vehicle> allvehicles = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Vehicle");
        while (rst.next()){
            allvehicles.add(new Vehicle(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9)));
        }
        return allvehicles;
    }

    @Override
    public boolean update(Vehicle entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Vehicle SET name = ?, type = ?, rent_price = ? , mileage = ? , first_aid_kit = ? , transmission = ?, " +
                "roadside_assistance = ?, available = ? WHERE vehicleId = ?",
                entity.getVehicleName(), entity.getType(), entity.getRent(), entity.getMilage(), entity.getFirstAidKit(),
                entity.getTransmission(), entity.getRoadAssistance(), entity.getAvailable(), entity.getVehicleId());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Vehicle WHERE vehicleId=?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT vehicleId FROM Vehicle ORDER BY vehicleId DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("vehicleId");
            int newCustomerId = Integer.parseInt(id.replace("V00-", "")) + 1;
            return String.format("V00-%03d", newCustomerId);
        } else {
            return "V00-001";
        }
    }

    @Override
    public Vehicle search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Vehicle> searchVehicle(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Vehicle WHERE vehicleId = ?", s);
        ArrayList<Vehicle> vehicleDetails = new ArrayList<>();
        while (resultSet.next()){
            vehicleDetails.add(new Vehicle(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                    resultSet.getString(8), resultSet.getString(9)));
        }
        return vehicleDetails;
    }

    @Override
    public ArrayList<VehicleStatus> getAllVehicleStatus() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleStatus> allvehicles = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM VehicleStatus");
        while (rst.next()){
            allvehicles.add(new VehicleStatus(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5), rst.getString(6), rst.getString(7)));
        }
        return allvehicles;
    }

}
