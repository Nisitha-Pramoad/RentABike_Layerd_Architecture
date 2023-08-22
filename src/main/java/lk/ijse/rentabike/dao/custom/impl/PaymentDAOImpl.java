package lk.ijse.rentabike.dao.custom.impl;

import lk.ijse.rentabike.dao.custom.PaymentDAO;
import lk.ijse.rentabike.dao.custom.impl.util.SQLUtil;
import lk.ijse.rentabike.entity.Customer;
import lk.ijse.rentabike.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Payment (paymentId, paymentAmount, paymentDescription, paymentDate, cId, bId) VALUES (?,?,?,?,?,?)",
                entity.getPayId(), entity.getPayAmount(), entity.getPayDescription(), entity.getPayDate(), entity.getcId(), entity.getbId());
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allPayments = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Payment ORDER BY PaymentId DESC");
        while (rst.next()) {
            allPayments.add(new Payment(rst.getString(1), rst.getDouble(2), rst.getString(3), rst.getString(4),
                    rst.getString(5), rst.getString(6)));
        }
        return allPayments;
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Payment SET paymentDescription = 'Cancel Payment.' WHERE bId = ?", entity.getbId());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT paymentId FROM Payment ORDER BY paymentId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("paymentId");
            int newCustomerId = Integer.parseInt(id.replace("P00-", "")) + 1;
            return String.format("P00-%03d", newCustomerId);
        } else {
            return "P00-001";
        }
    }

    @Override
    public Payment search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
