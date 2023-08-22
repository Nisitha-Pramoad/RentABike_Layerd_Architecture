package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.PaymentManagementBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.PaymentDAO;
import lk.ijse.rentabike.dto.PaymentDTO;
import lk.ijse.rentabike.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentManagementBOImpl implements PaymentManagementBO {
    PaymentDAO paymentDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> all = paymentDAO.getAll();
        ArrayList<PaymentDTO> arrayList= new ArrayList<>();
        for (Payment p : all) {
            arrayList.add(new PaymentDTO(p.getPayId(), p.getPayAmount(), p.getPayDescription(), p.getPayDate(), p.getcId(), p.getbId()));
        }
        return arrayList;
    }
}
