package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.SalaryManagementBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.EmployeeDAO;
import lk.ijse.rentabike.dao.custom.SalaryDAO;
import lk.ijse.rentabike.dto.AttendenceDTO;
import lk.ijse.rentabike.dto.EmployeeDTO;
import lk.ijse.rentabike.dto.SalaryDTO;
import lk.ijse.rentabike.entity.Attendence;
import lk.ijse.rentabike.entity.Employee;
import lk.ijse.rentabike.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryManagementBOImpl implements SalaryManagementBO {
    EmployeeDAO employeeDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    SalaryDAO salaryDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SALARY);

    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> all = employeeDAO.getAll();
        ArrayList<EmployeeDTO> arrayList= new ArrayList<>();
        for (Employee e : all) {
            arrayList.add(new EmployeeDTO(e.getEmployeeId(),e.getEmployeeTyped(),e.getName(), e.getNic(), e.getAddress(), e.getContact(), e.getEmail()));
        }
        return arrayList;
    }

    @Override
    public ArrayList<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> all = salaryDAO.getAll();
        ArrayList<SalaryDTO> arrayList= new ArrayList<>();
        for (Salary s : all) {
            arrayList.add(new SalaryDTO(s.getSalaryId(), s.getDescription(), s.getAmount(), s.getType(), s.getMonth(), s.getEmployeeId()));
        }
        return arrayList;
    }

    @Override
    public String generateNewSalaryID() throws SQLException, ClassNotFoundException {
        return salaryDAO.generateNewID();
    }

    @Override
    public boolean saveSalary(SalaryDTO sDTO) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(new Salary(sDTO.getSalaryId(), sDTO.getDescription(), sDTO.getAmount(), sDTO.getType(), sDTO.getMonth(), sDTO.getEmployeeId()));
    }

    @Override
    public boolean deleteSalary(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(id);
    }

    @Override
    public boolean updateSalary(SalaryDTO sDTO) throws SQLException, ClassNotFoundException {
        return salaryDAO.update(new Salary(sDTO.getSalaryId(), sDTO.getDescription(), sDTO.getAmount(), sDTO.getType(), sDTO.getMonth(), sDTO.getEmployeeId()));
    }

    @Override
    public SalaryDTO searchSalary(String salaryId) throws SQLException, ClassNotFoundException {
        Salary allSly = salaryDAO.search(salaryId);
        return new SalaryDTO(allSly.getSalaryId(), allSly.getDescription(), allSly.getAmount(), allSly.getType(), allSly.getMonth(),allSly.getEmployeeId());

    }

}
