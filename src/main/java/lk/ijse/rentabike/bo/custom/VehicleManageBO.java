package lk.ijse.rentabike.bo.custom;

import lk.ijse.rentabike.bo.SuperBO;
import lk.ijse.rentabike.dto.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleManageBO extends SuperBO {

    ArrayList<VehicleDTO> getALlVehicles() throws SQLException, ClassNotFoundException;
}
