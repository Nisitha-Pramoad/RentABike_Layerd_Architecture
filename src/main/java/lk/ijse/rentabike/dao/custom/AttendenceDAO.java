package lk.ijse.rentabike.dao.custom;

import lk.ijse.rentabike.dao.CrudDAO;
import lk.ijse.rentabike.entity.Attendence;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendenceDAO extends CrudDAO<Attendence,String> {
    ArrayList<Attendence> searchAttendence(String s) throws SQLException, ClassNotFoundException;

}
