package lk.ijse.rentabike.dao.custom.impl;

import lk.ijse.rentabike.dao.custom.impl.util.SQLUtil;
import lk.ijse.rentabike.dao.custom.UserDAO;
import lk.ijse.rentabike.entity.Payment;
import lk.ijse.rentabike.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO User (user_name,password) VALUES (?,?)", entity.getUser_name(), entity.getPassword());
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE User SET password = ? WHERE user_name = ?", entity.getPassword(), entity.getUser_name());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM User WHERE user_name = ?", s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM User WHERE user_name = ?", s);
        User user = new User();

        if (rst.next()) {
            user.setPassword(rst.getString("password"));
        }

        return user;
    }
}
