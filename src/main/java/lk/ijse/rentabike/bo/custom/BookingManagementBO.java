package lk.ijse.rentabike.bo.custom;

import lk.ijse.rentabike.bo.SuperBO;
import lk.ijse.rentabike.dto.BookingDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingManagementBO extends SuperBO {

    boolean cancelBooking(BookingDTO bookingDTO);
    ArrayList<BookingDTO> getAllBookings() throws SQLException, ClassNotFoundException;
}
