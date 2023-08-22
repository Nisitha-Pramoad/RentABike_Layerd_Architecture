package lk.ijse.rentabike.bo.custom.impl;

import com.mysql.cj.jdbc.JdbcConnection;
import lk.ijse.rentabike.bo.custom.BookingFillingBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.BookingDAO;
import lk.ijse.rentabike.dao.custom.CustomerDAO;
import lk.ijse.rentabike.dao.custom.PaymentDAO;
import lk.ijse.rentabike.dao.custom.VehicleStatusDAO;
import lk.ijse.rentabike.db.DBConnection;
import lk.ijse.rentabike.dto.BookingDTO;
import lk.ijse.rentabike.dto.CustomerDTO;
import lk.ijse.rentabike.dto.PaymentDTO;
import lk.ijse.rentabike.dto.VehicleStatusDTO;
import lk.ijse.rentabike.entity.Booking;
import lk.ijse.rentabike.entity.Customer;
import lk.ijse.rentabike.entity.Payment;
import lk.ijse.rentabike.entity.VehicleStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingFillingBOImpl implements BookingFillingBO {
    CustomerDAO customerDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    BookingDAO bookingDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.BOOKING);
    PaymentDAO paymentDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    VehicleStatusDAO vehicleStatusDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.VEHICLE_STATUS);

    @Override
    public boolean existBookingById (String id) throws SQLException, ClassNotFoundException {
        return bookingDAO.exist(id);
    }

    @Override
    public boolean saveBooking(CustomerDTO cDTO, BookingDTO bDTO, VehicleStatusDTO vDTO, PaymentDTO paDTO) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
        try {

            connection = DBConnection.getDbConnection().getConnection();
            /*if order id already exist*/
            if (bookingDAO.exist(bDTO.getBookingId())) {
                return false;
            }

            connection.setAutoCommit(false);

            Customer customer = new Customer(cDTO.getCustomerId(), cDTO.getFullName(), cDTO.getAge(), cDTO.getPhoneNumber(), cDTO.getEmail(), cDTO.getAddress(), cDTO.getCity(), cDTO.getCountry(), cDTO.getZipCode());
            boolean customerAdded = customerDAO.save(customer);
            if (!customerAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            Booking booking = new Booking(bDTO.getBookingId(), bDTO.getChooseBike(), bDTO.getPickUpLocation(), bDTO.getPickUpDate(), bDTO.getPickUpTime(), bDTO.getDropOffLocation(), bDTO.getDropOffDate(), bDTO.getDropOffTime(), bDTO.getBookingStatus());
            boolean bookingAdded = bookingDAO.save(booking);
            if (!bookingAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            //Search & Update Item
            Payment payment = new Payment(paDTO.getPaymentId(), paDTO.getPaymentAmount(), paDTO.getPaymentDescription(), paDTO.getPaymentDate(), paDTO.getcId(), paDTO.getbId());
            boolean paymentAdded = paymentDAO.save(payment);

             if (!paymentAdded) {
                 connection.rollback();
                 connection.setAutoCommit(true);
                 return false;
             }

             //update vehicle status
            VehicleStatus vehicleStatus = new VehicleStatus(vDTO.getName(), vDTO.getAvailable(), vDTO.getCustomer_id(), vDTO.getBooking_id());
            boolean vehicleUpdated = vehicleStatusDAO.updateVehicleStatus(vehicleStatus);
            if (!vehicleUpdated) {
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

    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

    public String generateNewBookingID() throws SQLException, ClassNotFoundException {
        return bookingDAO.generateNewID();
    }

    public String generateNewPaymentID() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNewID();
    }

    public double getBikeRate(String selectVehicle) throws SQLException, ClassNotFoundException {
        return vehicleStatusDAO.getRate(selectVehicle);
    }

    public ArrayList<VehicleStatusDTO> searchAvailableVehicle() throws SQLException, ClassNotFoundException {

        ArrayList<VehicleStatus> all = vehicleStatusDAO.searchAvailableVehicle();
        ArrayList<VehicleStatusDTO> arrayList = new ArrayList<>();
        for (VehicleStatus i : all) {
            arrayList.add(new VehicleStatusDTO( i.getName()));
        }
        return arrayList;
    }
}
