package lk.ijse.rentabike.bo.custom;

import lk.ijse.rentabike.bo.SuperBO;
import lk.ijse.rentabike.dto.BookingDTO;
import lk.ijse.rentabike.dto.CustomerDTO;
import lk.ijse.rentabike.dto.PaymentDTO;
import lk.ijse.rentabike.dto.VehicleStatusDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingFillingBO extends SuperBO {
    boolean saveBooking(CustomerDTO customerDTO, BookingDTO bookingDTO, VehicleStatusDTO vehicleStatusDTO, PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;

    String generateNewCustomerID() throws SQLException, ClassNotFoundException;

    String generateNewBookingID() throws SQLException, ClassNotFoundException;

    String generateNewPaymentID() throws SQLException, ClassNotFoundException;

    double getBikeRate(String selectVehicle) throws SQLException, ClassNotFoundException;

    boolean existBookingById(String code) throws SQLException, ClassNotFoundException;

    ArrayList<VehicleStatusDTO> searchAvailableVehicle() throws SQLException, ClassNotFoundException ;

}

