package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.AddEmployeeInformationBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.EmployeeDAO;
import lk.ijse.rentabike.dto.EmployeeDTO;
import lk.ijse.rentabike.dto.SalaryDTO;
import lk.ijse.rentabike.entity.Employee;
import lk.ijse.rentabike.entity.Salary;

import java.sql.SQLException;


public class AddEmployeeInformationBOImpl implements AddEmployeeInformationBO {
    EmployeeDAO employeeDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save( new Employee(dto.getEmployeeId(), dto.getEmployeeTyped(),dto.getName(), dto.getNic(), dto.getAddress(), dto.getContact(), dto.getEmail()));
    }

    @Override
    public String generateNewEmployeeID() throws SQLException, ClassNotFoundException{
        return employeeDAO.generateNewID();
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update( new Employee(dto.getEmployeeId(), dto.getEmployeeTyped(),dto.getName(), dto.getNic(), dto.getAddress(), dto.getContact(), dto.getEmail()));
    }

    @Override
    public EmployeeDTO searchEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        Employee allemp = employeeDAO.search(employeeId);
        return new EmployeeDTO(allemp.getEmployeeId(), allemp.getEmployeeTyped(),allemp.getName(), allemp.getNic(), allemp.getAddress(), allemp.getContact(), allemp.getEmail());

    }
}
