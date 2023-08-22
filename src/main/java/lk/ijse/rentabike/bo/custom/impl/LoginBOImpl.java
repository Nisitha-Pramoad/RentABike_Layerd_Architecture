package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.LoginBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.UserDAO;
import lk.ijse.rentabike.dto.UserDTO;
import lk.ijse.rentabike.entity.User;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean isUserValid(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        User userFromDB = userDAO.search(userDTO.getUser_name());

        if (userFromDB != null) {
            String password = userDTO.getPassword();

            // Compare the password using equals method
            return password.equals(userFromDB.getPassword());
        }

        return false;
    }

}
