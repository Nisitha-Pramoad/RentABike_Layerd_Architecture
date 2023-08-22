package lk.ijse.rentabike.bo.custom;

import lk.ijse.rentabike.bo.SuperBO;
import lk.ijse.rentabike.dto.EmployeeDTO;
import lk.ijse.rentabike.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryManagementBO extends SuperBO {
    ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException;
    ArrayList<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException;
    String generateNewSalaryID() throws SQLException, ClassNotFoundException;
    boolean saveSalary(SalaryDTO sDTO) throws SQLException, ClassNotFoundException;
    boolean deleteSalary(String id) throws SQLException, ClassNotFoundException;
    boolean updateSalary(SalaryDTO sDTO) throws SQLException, ClassNotFoundException;
    SalaryDTO searchSalary(String salaryId) throws SQLException, ClassNotFoundException;
}
