package lk.ijse.rentabike.bo;

import lk.ijse.rentabike.bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes {
        Login_Bo ,CUSTOMER_BO, BOOKINGFilling_BO, BookingManagement_BO, VEHICLESTATUSMANAGE_BO, VEHICLE_MANAGE, ADDVEHICLEINFORMATION_BO,
        CUSTOMERMANAGEMENT_BO, CUSTOMERINFORMATION_BO, EMPLOYEEMANAGEMENT_BO, ATTENDENCEMANAGEMENT_BO, SALARYMANAGEMENT_BO, ADDEMPLOYEEINFORMATION_BO,
        PAYMENT_MANAGEMENTBO, SETTING_BO, DASHBOARD_BO,
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes){
        switch (boTypes) {
            case Login_Bo:
                return (T) new LoginBOImpl();
            case CUSTOMER_BO:
                return (T) new CustomerBOImpl();
            case BOOKINGFilling_BO:
                return (T) new BookingFillingBOImpl();
            case BookingManagement_BO:
                return (T) new BookingManagementBOImpl();
            case VEHICLESTATUSMANAGE_BO:
                return (T) new VehicleStatusManageBOImpl();
            case VEHICLE_MANAGE:
                return (T) new VehicleManageBOImpl();
            case ADDVEHICLEINFORMATION_BO:
                return (T) new AddVehicleInformationBOImpl();
            case CUSTOMERMANAGEMENT_BO:
                return (T) new CustomerManagementBOImpl();
            case CUSTOMERINFORMATION_BO:
                return (T) new CustomerInformationBOImpl();
            case EMPLOYEEMANAGEMENT_BO:
                return (T) new EmployeeManagementBOImpl();
            case ATTENDENCEMANAGEMENT_BO:
                return (T) new AttendenceManagementBOImpl();
            case SALARYMANAGEMENT_BO:
                return (T) new SalaryManagementBOImpl();
            case ADDEMPLOYEEINFORMATION_BO:
                return (T) new AddEmployeeInformationBOImpl();
            case PAYMENT_MANAGEMENTBO :
                return (T) new PaymentManagementBOImpl();
            case SETTING_BO :
                return (T) new SettingBOImpl();
            case DASHBOARD_BO :
                return (T) new DashboardBOImpl();
            default:
                return null;
        }
    }
}
