package lk.ijse.rentabike.bo.custom.impl;

import lk.ijse.rentabike.bo.custom.DashboardBO;
import lk.ijse.rentabike.dao.DAOFactory;
import lk.ijse.rentabike.dao.custom.PaymentDAO;
import lk.ijse.rentabike.dao.custom.VehicleDAO;
import lk.ijse.rentabike.dto.PaymentDTO;
import lk.ijse.rentabike.dto.VehicleDTO;
import lk.ijse.rentabike.entity.Payment;
import lk.ijse.rentabike.entity.Vehicle;
import lk.ijse.rentabike.entity.VehicleStatus;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardBOImpl implements DashboardBO {
    PaymentDAO paymentDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    VehicleDAO vehicleDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    @Override
    public Map<String, Double> profitanalysis() throws SQLException, ClassNotFoundException{
        ArrayList<Payment> all = paymentDAO.getAll();
        Map<String, Double> profits = new HashMap<>();

        double todayProfit = 0;
        double thisWeekProfit = 0;
        double thisMonthProfit = 0;
        double thisYearProfit = 0;
        LocalDate today = LocalDate.now();

        for (Payment payment : all) {
            // Get the payment date and amount
            LocalDate paymentDate = LocalDate.parse(payment.getPayDate().toString());
            double profit = payment.getPayAmount();

            // Add the profit to the corresponding variable based on its date range
            if (paymentDate.equals(today)) {
                todayProfit += profit;
            }
            if (paymentDate.isAfter(today.minusDays(7))) {
                thisWeekProfit += profit;
            }
            if (paymentDate.isAfter(today.minusDays(30))) {
                thisMonthProfit += profit;
            }
            if (paymentDate.isAfter(today.minusDays(365))) {
                thisYearProfit += profit;
            }
        }

        profits.put("Today", todayProfit);
        profits.put("This week", thisWeekProfit);
        profits.put("This month", thisMonthProfit);
        profits.put("This year", thisYearProfit);

        return profits;
    }


    @Override
    public Map<String, Map<String, Integer>> getVehicleStatistics() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleStatus> allVehicles = vehicleDAO.getAllVehicleStatus();
        Map<String, Map<String, Integer>> vehicleStatisticsMap = new HashMap<>();

        // Process each vehicle
        for (VehicleStatus vehicle : allVehicles) {
            String vehicleType = vehicle.getType();
            String availability = vehicle.getAvailable();

            // Check if the vehicle type exists in the statistics map
            if (!vehicleStatisticsMap.containsKey(vehicleType)) {
                // If not, create a new counts map for the vehicle type
                Map<String, Integer> vehicleCounts = new HashMap<>();
                vehicleCounts.put("Count", 0);
                vehicleCounts.put("Available", 0);
                vehicleStatisticsMap.put(vehicleType, vehicleCounts);
            }

            // Retrieve the counts map for the vehicle type
            Map<String, Integer> counts = new HashMap<>(vehicleStatisticsMap.get(vehicleType));

            // Update counts for the current vehicle
            counts.put("Count", counts.get("Count") + 1);
            if (availability.equalsIgnoreCase("available")) {
                counts.put("Available", counts.get("Available") + 1);
            }

            // Update the statistics map with the updated counts
            vehicleStatisticsMap.put(vehicleType, counts);
        }


        return vehicleStatisticsMap;
    }



}
