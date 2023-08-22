package lk.ijse.rentabike.bo.custom;

import lk.ijse.rentabike.bo.SuperBO;
import lk.ijse.rentabike.dto.VehicleStatusDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleStatusManageBO extends SuperBO {
    boolean vehicleReturnManage (VehicleStatusDTO vehicleStatusDTO);
    ArrayList<VehicleStatusDTO> getAllVehicles() throws SQLException, ClassNotFoundException;
}
