package lk.ijse.rentabike.dao.custom.impl;

import lk.ijse.rentabike.dao.custom.VehicleStatusDAO;
import lk.ijse.rentabike.dao.custom.impl.util.SQLUtil;
import lk.ijse.rentabike.entity.Customer;
import lk.ijse.rentabike.entity.VehicleStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleStatusDAOImpl implements VehicleStatusDAO {
    @Override
    public boolean save(VehicleStatus vto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<VehicleStatus> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleStatus> vehicleStatuses = new ArrayList<>();
        ResultSet rst =SQLUtil.execute("SELECT * FROM VehicleStatus");
        while (rst.next()){
            vehicleStatuses.add(new VehicleStatus(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5), rst.getString(6), rst.getString(7)));
        }
        return vehicleStatuses;
    }

    @Override
    public boolean update(VehicleStatus entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE VehicleStatus SET available = 'available' WHERE booking_id = ?", entity.getBookingdId());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public VehicleStatus search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    public double getRate(String selectVehicle) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT rent_price FROM VehicleStatus WHERE name = ?", selectVehicle);
        double rate = 0.0;  // Initialize rate with a default value

        if (rst.next()) {
            rate = rst.getDouble("rent_price");
        }

        return rate;
    }

    public ArrayList<VehicleStatus> searchAvailableVehicle() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT name FROM VehicleStatus WHERE available = 'available'");
        ArrayList<VehicleStatus> allIVehicles = new ArrayList<>();
        while (rst.next()) {
            String name = rst.getString("name");
            VehicleStatus vehicleStatus = new VehicleStatus();
            vehicleStatus.setName(name);
            allIVehicles.add(vehicleStatus);
        }
        return allIVehicles;
    }

    public boolean updateVehicleStatus(VehicleStatus entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE VehicleStatus SET available = ?, customer_id = ?, booking_id = ? WHERE name = ?", entity.getAvailable(), entity.getCustomerId(), entity.getBookingdId(),  entity.getName());
    }

}
