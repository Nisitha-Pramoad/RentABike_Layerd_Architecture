package lk.ijse.rentabike.bo.custom;

import lk.ijse.rentabike.bo.SuperBO;
import lk.ijse.rentabike.dto.UserDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean isUserValid(UserDTO userDTO) throws SQLException, ClassNotFoundException ;
}
