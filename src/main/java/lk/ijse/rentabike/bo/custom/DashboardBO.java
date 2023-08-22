package lk.ijse.rentabike.bo.custom;

import lk.ijse.rentabike.bo.SuperBO;
import lk.ijse.rentabike.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface DashboardBO extends SuperBO {
    Map<String, Double> profitanalysis() throws SQLException, ClassNotFoundException;
    Map<String, Map<String, Integer>> getVehicleStatistics() throws SQLException, ClassNotFoundException;
}
