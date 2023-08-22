package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.VehicleManageBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.VehicleDAO;
import lk.ijse.rentabike.dto.VehicleDTO;
import lk.ijse.rentabike.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleManageBOImpl implements VehicleManageBO {
    VehicleDAO vehicleDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    public ArrayList<VehicleDTO> getALlVehicles() throws SQLException, ClassNotFoundException {
        ArrayList<Vehicle> allvehicles = vehicleDAO.getAll();
        ArrayList<VehicleDTO> arrayList = new ArrayList<>();
        for (Vehicle v : allvehicles) {
            arrayList.add(new VehicleDTO(v.getVehicleId(), v.getVehicleName(), v.getType(), v.getRent(), v.getMilage(), v.getFirstAidKit(), v.getTransmission(), v.getRoadAssistance(), v.getAvailable()));
        }
        return arrayList;
    }

    //vs
}
