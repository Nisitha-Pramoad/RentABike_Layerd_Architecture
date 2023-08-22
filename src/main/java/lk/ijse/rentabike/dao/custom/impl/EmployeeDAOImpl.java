package lk.ijse.rentabike.dao.custom.impl;

import lk.ijse.rentabike.dao.custom.EmployeeDAO;
import lk.ijse.rentabike.dao.custom.impl.util.SQLUtil;
import lk.ijse.rentabike.entity.Employee;
import lk.ijse.rentabike.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee(employeeId, employeeTyped ,name, nic, address, contact, email) VALUES(?, ?, ?, ?, ?, ?, ?)",
                dto.getEmployeeId(), dto.getEmployeeTyped(), dto.getName(), dto.getNic(), dto.getAddress(), dto.getContact(), dto.getEmail());
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployee = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee");
        while (rst.next()) {
            allEmployee.add(new Employee(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4),
                    rst.getString(5), rst.getString(6), rst.getString(7)));
        }
        return allEmployee;
    }

    @Override
    public boolean update(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Employee SET employeeTyped = ?, name = ?, nic = ?, address = ?, contact = ?, email = ? WHERE employeeId = ?",
                dto.getEmployeeTyped(), dto.getName(), dto.getNic(), dto.getAddress(), dto.getContact(), dto.getEmail(), dto.getEmployeeId());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE employeeId = ?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT employeeId FROM Employee ORDER BY employeeId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("employeeId");
            int newCustomerId = Integer.parseInt(id.replace("E00-", "")) + 1;
            return String.format("E00-%03d", newCustomerId);
        } else {
            return "E00-001";
        }
    }

    @Override
    public Employee search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee WHERE employeeId = ?", s);
        Employee employee = new Employee();

        if (rst.next()) {
            employee.setEmployeeId(rst.getString("employeeId"));
            employee.setEmployeeTyped(rst.getString("employeeTyped"));
            employee.setName(rst.getString("name"));
            employee.setNic(rst.getString("nic"));
            employee.setAddress(rst.getString("address"));
            employee.setContact(rst.getString("contact"));
            employee.setEmail(rst.getString("email"));
        }

        return employee;
    }
}
