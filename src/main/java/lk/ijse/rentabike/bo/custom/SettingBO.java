package lk.ijse.rentabike.bo.custom;

import lk.ijse.rentabike.bo.SuperBO;
import lk.ijse.rentabike.dto.UserDTO;

import java.sql.SQLException;

public interface SettingBO extends SuperBO {
    boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
}
