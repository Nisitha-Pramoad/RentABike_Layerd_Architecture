package lk.ijse.rentabike.dao.custom.impl;

import lk.ijse.rentabike.dao.custom.impl.util.SQLUtil;
import lk.ijse.rentabike.dao.custom.CustomerDAO;
import lk.ijse.rentabike.entity.Customer;
import lk.ijse.rentabike.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer (customerId, name, age, contact, email, address, city, country, zip_code) VALUES (?,?,?,?,?,?,?,?,?)",
                entity.getCustomerId(), entity.getFullName(), entity.getAge(), entity.getPhoneNumber(), entity.getEmail(), entity.getAddress(),
                entity.getCity(), entity.getCountry(), entity.getZipCode());
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer ORDER BY customerId DESC");
        while (rst.next()) {
            allCustomers.add(new Customer(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9)));
        }
        return allCustomers;
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET name = ?, age = ?, contact = ?, email = ?, address = ?, city = ?, country = ?, " +
                        "zip_code = ? WHERE customerId = ?", entity.getFullName(), entity.getAge(), entity.getPhoneNumber(), entity.getEmail(),
                entity.getAddress(), entity.getCity(), entity.getCountry(), entity.getZipCode(), entity.getCustomerId());
    }

    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer WHERE customerId=?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT customerId FROM Customer ORDER BY customerId DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("customerId");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public ArrayList<Customer> searchCustomer(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE customerId = ?", s);
        ArrayList<Customer> customerDetails = new ArrayList<>();
        if (rst.next()) {
            customerDetails.add(new Customer(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getString(4),
                    rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8),
                    rst.getString(9)));
        }
        return customerDetails;
    }
}
