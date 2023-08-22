package lk.ijse.rentabike.dao.custom.impl;

import lk.ijse.rentabike.dao.custom.BookingDAO;
import lk.ijse.rentabike.dao.custom.impl.util.SQLUtil;
import lk.ijse.rentabike.entity.Booking;
import lk.ijse.rentabike.entity.Customer;
import lk.ijse.rentabike.entity.VehicleStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAOImpl implements BookingDAO {
    @Override
    public boolean save(Booking entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Booking (bookingId, chooseBike, PickUpLocation, pickUpDate, pickUpTime, dropOffLocation, dropOffDate, dropOffTime, bookingStatus) VALUES (?,?,?,?,?,?,?,?,?)",
                entity.getBookingId(), entity.getChooseBike(), entity.getPickUpLocation(), entity.getPickUpDate(), entity.getPickUpTime(), entity.getDropOffLocation(), entity.getDropOffDate(), entity.getDropOffTime(), entity.getBookingStatus());
    }

    @Override
    public ArrayList<Booking> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Booking> allBookings = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Booking");
        while (rst.next()){
            allBookings.add(new Booking(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5), rst.getString(6), rst.getDate(7), rst.getString(8), rst.getString(9)));
        }
        return allBookings;
    }

    @Override
    public boolean update(Booking entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Booking SET BookingStatus = 'cancel' WHERE bookingId = ?", entity.getBookingId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT bookingId FROM Booking WHERE bookingId=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT bookingId FROM Booking ORDER BY bookingId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("bookingId");
            int newCustomerId = Integer.parseInt(id.replace("B00-", "")) + 1;
            return String.format("B00-%03d", newCustomerId);
        } else {
            return "B00-001";
        }
    }

    @Override
    public Booking search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean updateReturnVehicle(Booking entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Booking SET BookingStatus = 'cancel' WHERE bookingId = ?", entity.getBookingId());
    }
}
