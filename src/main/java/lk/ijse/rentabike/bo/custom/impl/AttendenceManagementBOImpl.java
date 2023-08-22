package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.AttendenceManagementBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.AttendenceDAO;
import lk.ijse.rentabike.dao.custom.EmployeeDAO;
import lk.ijse.rentabike.dto.AttendenceDTO;
import lk.ijse.rentabike.dto.EmployeeDTO;
import lk.ijse.rentabike.dto.VehicleDTO;
import lk.ijse.rentabike.entity.Attendence;
import lk.ijse.rentabike.entity.Employee;
import lk.ijse.rentabike.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendenceManagementBOImpl implements AttendenceManagementBO {
    EmployeeDAO employeeDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    AttendenceDAO attendenceDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ATTENDENCE);

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
    public ArrayList<AttendenceDTO> getAllAttendence() throws SQLException, ClassNotFoundException {
        ArrayList<Attendence> all = attendenceDAO.getAll();
        ArrayList<AttendenceDTO> arrayList= new ArrayList<>();
        for (Attendence a : all) {
            arrayList.add(new AttendenceDTO(a.getAttendenceId(), a.getDate(), a.getHoliday(), a.getSignInTime(), a.getSignOutTime(), a.getEmployeeId()));
        }
        return arrayList;
    }

    @Override
    public String generateNewAttendenceID() throws SQLException, ClassNotFoundException {
        return attendenceDAO.generateNewID();
    }

    public boolean saveAttendence(AttendenceDTO aDTO) throws SQLException, ClassNotFoundException {
        return attendenceDAO.save(new Attendence(aDTO.getAttendenceId(),  aDTO.getDate(), aDTO.getHoliday(), aDTO.getSignInTime(), aDTO.getSignOutTime(), aDTO.getEmployeeId()));
    }

    @Override
    public boolean deleteAttendence(String id) throws SQLException, ClassNotFoundException {
        return attendenceDAO.delete(id);
    }

    @Override
    public boolean updateAttendence(AttendenceDTO aDTO) throws SQLException, ClassNotFoundException {
        return attendenceDAO.update( new Attendence(aDTO.getAttendenceId(),  aDTO.getDate(), aDTO.getHoliday(), aDTO.getSignInTime(), aDTO.getSignOutTime(), aDTO.getEmployeeId()));
    }

    @Override
    public ArrayList<AttendenceDTO> searchAttendence(String attendenceId) throws SQLException, ClassNotFoundException {
        ArrayList<Attendence> all = attendenceDAO.searchAttendence(attendenceId);
        ArrayList<AttendenceDTO> arrayList = new ArrayList<>();
        for (Attendence aDTO : all) {
            arrayList.add(new AttendenceDTO(aDTO.getAttendenceId(),  aDTO.getDate(), aDTO.getHoliday(), aDTO.getSignInTime(), aDTO.getSignOutTime(), aDTO.getEmployeeId()));
        }
        return arrayList;
    }
}
