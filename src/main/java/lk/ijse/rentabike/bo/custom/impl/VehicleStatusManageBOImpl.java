package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.VehicleStatusManageBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.BookingDAO;
import lk.ijse.rentabike.dao.custom.VehicleStatusDAO;
import lk.ijse.rentabike.db.DBConnection;
import lk.ijse.rentabike.dto.VehicleStatusDTO;
import lk.ijse.rentabike.entity.Booking;
import lk.ijse.rentabike.entity.VehicleStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleStatusManageBOImpl implements VehicleStatusManageBO {
    BookingDAO bookingDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.BOOKING);
    VehicleStatusDAO vehicleStatusDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.VEHICLE_STATUS);
    public boolean vehicleReturnManage (VehicleStatusDTO vehicleStatusDTO){
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            VehicleStatus vehicleStatus = new VehicleStatus();
            vehicleStatus.setBookingdId(vehicleStatusDTO.getBooking_id());
            boolean isVehicleStatusUpdated = vehicleStatusDAO.update(vehicleStatus);
            if (!isVehicleStatusUpdated) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            Booking booking = new Booking(vehicleStatusDTO.getBooking_id());
            boolean isBookingUpdated = bookingDAO.updateReturnVehicle(booking);
            if (!isBookingUpdated) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<VehicleStatusDTO> getAllVehicles() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleStatus> allVehicles = vehicleStatusDAO.getAll();
        ArrayList<VehicleStatusDTO> arrayList = new ArrayList<>();
        for (VehicleStatus c: allVehicles) {
            arrayList.add(new VehicleStatusDTO(c.getVehicleId(), c.getName(), c.getType(), c.getRentPrice(), c.getCustomerId(), c.getBookingdId(), c.getAvailable()));
        }
        return arrayList;
    }
}
