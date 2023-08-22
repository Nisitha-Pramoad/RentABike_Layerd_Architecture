package lk.ijse.rentabike.bo.custom;

import lk.ijse.rentabike.bo.SuperBO;
import lk.ijse.rentabike.dto.AttendenceDTO;
import lk.ijse.rentabike.dto.EmployeeDTO;
import lk.ijse.rentabike.entity.Attendence;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendenceManagementBO extends SuperBO {
    ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException;
    ArrayList<AttendenceDTO> getAllAttendence() throws SQLException, ClassNotFoundException;
    String generateNewAttendenceID() throws SQLException, ClassNotFoundException;
    boolean saveAttendence(AttendenceDTO attendenceDTO) throws SQLException, ClassNotFoundException;
    boolean deleteAttendence(String id) throws SQLException, ClassNotFoundException;
    boolean updateAttendence(AttendenceDTO ADto) throws SQLException, ClassNotFoundException;
    ArrayList<AttendenceDTO> searchAttendence(String attendenceId) throws SQLException, ClassNotFoundException;
}
