package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.BookingManagementBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.BookingDAO;
import lk.ijse.rentabike.dao.custom.PaymentDAO;
import lk.ijse.rentabike.dao.custom.VehicleStatusDAO;
import lk.ijse.rentabike.db.DBConnection;
import lk.ijse.rentabike.dto.BookingDTO;
import lk.ijse.rentabike.entity.Booking;
import lk.ijse.rentabike.entity.Payment;
import lk.ijse.rentabike.entity.VehicleStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingManagementBOImpl implements BookingManagementBO {
    BookingDAO bookingDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.BOOKING);
    VehicleStatusDAO vehicleStatusDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.VEHICLE_STATUS);
    PaymentDAO paymentDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    public boolean cancelBooking(BookingDTO bookingDTO){
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);

            Booking booking = new Booking(bookingDTO.getBookingId());
            boolean isBookingUpdated = bookingDAO.update(booking);
            if (!isBookingUpdated) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            VehicleStatus vehicleStatus = new VehicleStatus();
            vehicleStatus.setBookingdId(bookingDTO.getBookingId());
            boolean isVehicleStatusUpdated = vehicleStatusDAO.update(vehicleStatus);
            if (!isVehicleStatusUpdated) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            //Update Vehicle
            Payment payment = new Payment(bookingDTO.getBookingId());
            boolean isPaymentUpdated = paymentDAO.update(payment);
            if (!isPaymentUpdated) {
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

    @Override
    public ArrayList<BookingDTO> getAllBookings() throws SQLException, ClassNotFoundException {
        ArrayList<Booking> all = bookingDAO.getAll();
        ArrayList<BookingDTO> arrayList = new ArrayList<>();
        for (Booking b : all) {
            arrayList.add(new BookingDTO(b.getBookingId(), b.getChooseBike(), b.getPickUpLocation(), b.getPickUpDate(), b.getPickUpTime(), b.getDropOffLocation(), b.getDropOffDate(), b.getDropOffTime(), b.getBookingStatus()));
        }
        return arrayList;
    }
}
