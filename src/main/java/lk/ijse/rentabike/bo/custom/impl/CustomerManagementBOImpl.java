package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.CustomerManagementBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.CustomerDAO;
import lk.ijse.rentabike.dto.CustomerDTO;
import lk.ijse.rentabike.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManagementBOImpl implements CustomerManagementBO {
    CustomerDAO customerDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> arrayList= new ArrayList<>();
        for (Customer c : all) {
            arrayList.add(new CustomerDTO(c.getCustomerId(),c.getFullName(),c.getAge(), c.getPhoneNumber(), c.getEmail(), c.getAddress(), c.getCity(), c.getCountry(), c.getZipCode()));
        }
        return arrayList;
    }
}
