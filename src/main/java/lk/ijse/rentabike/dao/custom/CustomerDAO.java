package lk.ijse.rentabike.dao.custom;

import lk.ijse.rentabike.dao.CrudDAO;
import lk.ijse.rentabike.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    ArrayList<Customer> searchCustomer(String s) throws SQLException, ClassNotFoundException;
}
