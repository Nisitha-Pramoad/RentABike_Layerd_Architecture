package lk.ijse.rentabike.dao.custom;

import lk.ijse.rentabike.dao.CrudDAO;
import lk.ijse.rentabike.entity.Vehicle;
import lk.ijse.rentabike.entity.VehicleStatus;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleDAO extends CrudDAO<Vehicle,String> {
    ArrayList<Vehicle> searchVehicle(String s) throws SQLException, ClassNotFoundException;
    ArrayList<VehicleStatus> getAllVehicleStatus() throws SQLException, ClassNotFoundException;
}
