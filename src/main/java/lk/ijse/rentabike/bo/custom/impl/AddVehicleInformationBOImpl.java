package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.AddVehicleInformationBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.VehicleDAO;
import lk.ijse.rentabike.dto.VehicleDTO;
import lk.ijse.rentabike.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddVehicleInformationBOImpl implements AddVehicleInformationBO {
    VehicleDAO vehicleDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);


    public String generateNewVehicleID() throws SQLException, ClassNotFoundException{
        return vehicleDAO.generateNewID();
    }

    public boolean saveNewVehicle(VehicleDTO  vDTO) throws SQLException, ClassNotFoundException{
        return vehicleDAO.save(new Vehicle(vDTO.getVehicleId(), vDTO.getVehicleName(), vDTO.getType(), vDTO.getRent(), vDTO.getMilage(), vDTO.getFirstAidKit(), vDTO.getTransmission(), vDTO.getRoadAssistance(), vDTO.getAvailable()));
    }

    public boolean deleteVehicle(String vehicleId) throws SQLException, ClassNotFoundException{
        return vehicleDAO.delete(vehicleId);
    }

    @Override
    public boolean updateVehicle(VehicleDTO vDTO) throws SQLException, ClassNotFoundException {
        return vehicleDAO.update(new Vehicle(vDTO.getVehicleId(), vDTO.getVehicleName(), vDTO.getType(), vDTO.getRent(), vDTO.getMilage(), vDTO.getFirstAidKit(), vDTO.getTransmission(), vDTO.getRoadAssistance(), vDTO.getAvailable()));
    }

    @Override
    public ArrayList<VehicleDTO> searchVehicle(String vehicleId) throws SQLException, ClassNotFoundException {
        ArrayList<Vehicle> all = vehicleDAO.searchVehicle(vehicleId);
        ArrayList<VehicleDTO> arrayList = new ArrayList<>();
        for (Vehicle v : all) {
            arrayList.add(new VehicleDTO(v.getVehicleId(), v.getVehicleName(), v.getType(), v.getRent(), v.getMilage(), v.getFirstAidKit(), v.getTransmission(), v.getRoadAssistance(), v.getAvailable()));
        }
        return arrayList;
    }
}
