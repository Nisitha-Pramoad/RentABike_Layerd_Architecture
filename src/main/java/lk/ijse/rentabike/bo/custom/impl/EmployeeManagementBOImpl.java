package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.EmployeeManagementBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.EmployeeDAO;
import lk.ijse.rentabike.dto.EmployeeDTO;
import lk.ijse.rentabike.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeManagementBOImpl implements EmployeeManagementBO {
    EmployeeDAO employeeDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> all = employeeDAO.getAll();
        ArrayList<EmployeeDTO> arrayList= new ArrayList<>();
        for (Employee e : all) {
            arrayList.add(new EmployeeDTO(e.getEmployeeId(),e.getEmployeeTyped(),e.getName(), e.getNic(), e.getAddress(), e.getContact(), e.getEmail()));
        }
        return arrayList;
    }
}
