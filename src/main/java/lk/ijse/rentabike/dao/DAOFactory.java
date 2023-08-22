package lk.ijse.rentabike.dao;

import lk.ijse.rentabike.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER, CUSTOMER, BOOKING, VEHICLE_STATUS, PAYMENT, VEHICLE, EMPLOYEE, ATTENDENCE, SALARY,
    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case USER:
                return (T) new UserDAOImpl();
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case BOOKING :
                return (T) new BookingDAOImpl();
            case PAYMENT :
                return (T) new PaymentDAOImpl();
            case VEHICLE_STATUS:
                return (T) new VehicleStatusDAOImpl();
            case VEHICLE :
                return (T) new VehicleDAOImpl();
            case EMPLOYEE :
                return (T) new EmployeeDAOImpl();
            case ATTENDENCE :
                return (T) new AttendenceDAOImpl();
            case SALARY :
                return (T) new SalaryDAOImpl();
            default:
                return null;
        }
    }
}
