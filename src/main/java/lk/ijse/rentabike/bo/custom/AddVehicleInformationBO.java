package lk.ijse.rentabike.bo.custom;

import lk.ijse.rentabike.bo.SuperBO;
import lk.ijse.rentabike.dto.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddVehicleInformationBO extends SuperBO {

    String generateNewVehicleID() throws SQLException, ClassNotFoundException;
    boolean saveNewVehicle(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException;
    boolean deleteVehicle(String vehicleId) throws SQLException, ClassNotFoundException;
    boolean updateVehicle(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException;
    ArrayList<VehicleDTO> searchVehicle(String vehicleId) throws SQLException, ClassNotFoundException;
}
