package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.SettingBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.UserDAO;
import lk.ijse.rentabike.dto.UserDTO;
import lk.ijse.rentabike.entity.User;

import java.sql.SQLException;

public class SettingBOImpl implements SettingBO {
    UserDAO userDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.save( new User(dto.getUser_name(), dto.getPassword()));
    }

    @Override
    public boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.update( new User(dto.getUser_name(), dto.getPassword()));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }
}
