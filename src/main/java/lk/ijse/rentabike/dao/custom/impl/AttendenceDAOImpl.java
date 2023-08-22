package lk.ijse.rentabike.dao.custom.impl;

import lk.ijse.rentabike.dao.custom.AttendenceDAO;
import lk.ijse.rentabike.dao.custom.impl.util.SQLUtil;
import lk.ijse.rentabike.entity.Attendence;
import lk.ijse.rentabike.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendenceDAOImpl implements AttendenceDAO {
    @Override
    public boolean save(Attendence entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Attendance (attendenceId, date, holiday, signInTime, signOutTime, employeeId) VALUES (?,?,?,?,?,?)",
                entity.getAttendenceId(), entity.getDate(), entity.getHoliday(), entity.getSignInTime(), entity.getSignOutTime(), entity.getEmployeeId());
    }

    @Override
    public ArrayList<Attendence> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Attendence> allAttendence = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Attendance ORDER BY attendenceId DESC");
        while (rst.next()) {
            allAttendence.add(new Attendence(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6)));
        }
        return allAttendence;
    }

    @Override
    public boolean update(Attendence entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Attendance SET date = ?, holiday = ?, signInTime = ?, signOutTime = ?, employeeId = ? WHERE attendenceId = ?",
                entity.getDate(), entity.getHoliday(), entity.getSignInTime(), entity.getSignOutTime(), entity.getEmployeeId(), entity.getAttendenceId());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Attendance WHERE attendenceId = ?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT attendenceId FROM Attendance ORDER BY attendenceId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("attendenceId");
            int newCustomerId = Integer.parseInt(id.replace("A00-", "")) + 1;
            return String.format("A00-%03d", newCustomerId);
        } else {
            return "A00-001";
        }
    }

    @Override
    public Attendence search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    public ArrayList<Attendence> searchAttendence(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Attendance WHERE attendenceId = ?", s);
        ArrayList<Attendence> attendenceDetails = new ArrayList<>();
        while (rst.next()){
            attendenceDetails.add(new Attendence(rst.getString(1), rst.getString(2), rst.getString(3),
                    rst.getString(4), rst.getString(5), rst.getString(6)));
        }
        return attendenceDetails;
    }
}
