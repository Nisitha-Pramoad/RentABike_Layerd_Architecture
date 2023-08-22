package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.CustomerInformationBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.CustomerDAO;
import lk.ijse.rentabike.dto.CustomerDTO;
import lk.ijse.rentabike.dto.VehicleDTO;
import lk.ijse.rentabike.entity.Customer;
import lk.ijse.rentabike.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerInformationBOImpl implements CustomerInformationBO {
    CustomerDAO customerDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean saveCustomer(CustomerDTO cDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(cDTO.getCustomerId(), cDTO.getFullName(), cDTO.getAge(), cDTO.getPhoneNumber(), cDTO.getEmail(), cDTO.getAddress(), cDTO.getCity(), cDTO.getCountry(), cDTO.getZipCode()));
    }

    public String generateNewCustomerID() throws SQLException, ClassNotFoundException{
        return customerDAO.generateNewID();
    }

    @Override
    public boolean updateCustomer(CustomerDTO cDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(cDTO.getCustomerId(), cDTO.getFullName(), cDTO.getAge(), cDTO.getPhoneNumber(), cDTO.getEmail(), cDTO.getAddress(), cDTO.getCity(), cDTO.getCountry(), cDTO.getZipCode()));
    }

    @Override
    public ArrayList<CustomerDTO> searchCustomer(String customerId) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.searchCustomer(customerId);
        ArrayList<CustomerDTO> arrayList = new ArrayList<>();
        for (Customer c : all) {
            arrayList.add(new CustomerDTO(c.getCustomerId(), c.getFullName(), c.getAge(), c.getPhoneNumber(), c.getEmail(), c.getAddress(), c.getCity(), c.getCountry(), c.getZipCode()));
        }
        return arrayList;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }
}
