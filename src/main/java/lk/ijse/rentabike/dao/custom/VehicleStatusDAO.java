package lk.ijse.rentabike.dao.custom;

import lk.ijse.rentabike.dao.CrudDAO;
import lk.ijse.rentabike.entity.Customer;
import lk.ijse.rentabike.entity.VehicleStatus;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleStatusDAO extends CrudDAO<VehicleStatus,String> {
    public double getRate(String selectVehicle) throws SQLException, ClassNotFoundException;

    ArrayList<VehicleStatus> searchAvailableVehicle() throws SQLException, ClassNotFoundException;

    boolean updateVehicleStatus(VehicleStatus entity) throws SQLException, ClassNotFoundException;
}
