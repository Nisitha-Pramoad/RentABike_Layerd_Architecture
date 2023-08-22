package lk.ijse.rentabike.bo.custom;

import lk.ijse.rentabike.bo.SuperBO;
import lk.ijse.rentabike.dto.EmployeeDTO;
import lk.ijse.rentabike.dto.SalaryDTO;

import java.sql.SQLException;

public interface AddEmployeeInformationBO extends SuperBO {
    boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;
    String generateNewEmployeeID() throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;
    EmployeeDTO searchEmployee(String employeeId) throws SQLException, ClassNotFoundException;
}
