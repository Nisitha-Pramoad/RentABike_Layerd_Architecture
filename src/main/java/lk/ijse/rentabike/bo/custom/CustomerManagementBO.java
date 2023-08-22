package lk.ijse.rentabike.bo.custom;

import lk.ijse.rentabike.bo.SuperBO;
import lk.ijse.rentabike.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerManagementBO extends SuperBO {
    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
}
