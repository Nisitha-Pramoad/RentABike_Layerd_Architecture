package lk.ijse.rentabike.bo.custom;

import lk.ijse.rentabike.bo.SuperBO;
import lk.ijse.rentabike.dto.CustomerDTO;
import lk.ijse.rentabike.dto.VehicleDTO;
import lk.ijse.rentabike.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerInformationBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    String generateNewCustomerID() throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> searchCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

}
