package lk.ijse.rentabike.dao.custom;

import lk.ijse.rentabike.dao.CrudDAO;
import lk.ijse.rentabike.entity.Booking;
import lk.ijse.rentabike.entity.VehicleStatus;

import java.sql.SQLException;

public interface BookingDAO extends CrudDAO<Booking,String> {
    boolean updateReturnVehicle(Booking entity) throws SQLException, ClassNotFoundException;

}
