package lk.ijse.rentabike.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rentabike.bo.BoFactory;
import lk.ijse.rentabike.bo.custom.DashboardBO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class DashboardFormcontroller implements Initializable {

    @FXML
    private JFXButton billingbtn;

    @FXML
    private JFXButton bookingbtn;

    @FXML
    private JFXButton customerbtn;

    @FXML
    private JFXButton dashboardbtn;

    @FXML
    private JFXButton employeebtn;

    @FXML
    private Label lblAllBicycleCount;

    @FXML
    private Label lblAllCarsCount;

    @FXML
    private Label lblAllScootersCount;

    @FXML
    private Label lblAllThreewheelCount;

    @FXML
    private Label lblAvailableBicycle;

    @FXML
    private Label lblAvailableBikes;

    @FXML
    private Label lblAvailableCars;

    @FXML
    private Label lblAvailableScooters;

    @FXML
    private Label lblAvailableThreeWheels;

    @FXML
    private Label lblDayProfit;

    @FXML
    private Label lblMonthlyProfits;

    @FXML
    private Label lblPresent;

    @FXML
    private Label lblTotalBikes;

    @FXML
    private Label lblTotalEmployee;

    @FXML
    private Label lblWeeklyProfit;

    @FXML
    private Label lblYearlyProfits;

    @FXML
    private JFXButton logoutbtn;

    @FXML
    private JFXButton reportbtn;

    @FXML
    private JFXButton settingbtn;

    @FXML
    private JFXButton vehiclebtn;
    DashboardBO dashboardBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.DASHBOARD_BO);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            profitAnalysis();
            VehicleStatistics();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void profitAnalysis() throws SQLException, ClassNotFoundException {
        Map<String, Double> profits = dashboardBO.profitanalysis();
        double todayProfit = profits.get("Today");
        double thisWeekProfit = profits.get("This week");
        double thisMonthProfit = profits.get("This month");
        double thisYearProfit = profits.get("This year");

        lblDayProfit.setText(Double.toString(todayProfit));
        lblWeeklyProfit.setText(Double.toString(thisWeekProfit));
        lblMonthlyProfits.setText(Double.toString(thisMonthProfit));
        lblYearlyProfits.setText(Double.toString(thisYearProfit));
    }

    private void VehicleStatistics() throws SQLException, ClassNotFoundException {
        Map<String, Map<String, Integer>> vehicleStatistics = dashboardBO.getVehicleStatistics();

        Map<String, Integer> bikeStatistics = vehicleStatistics.get("Bikes");
        int totalCount = bikeStatistics.get("Count");
        int availableCount = bikeStatistics.get("Available");
        lblTotalBikes.setText(String.valueOf(totalCount));
        lblAvailableBikes.setText(String.valueOf(availableCount));

        Map<String, Integer> scooterStatistics = vehicleStatistics.get("Scooter");
        int scootertotalCount = scooterStatistics.get("Count");
        int scooteravailableCount = scooterStatistics.get("Available");
        lblAllScootersCount.setText(String.valueOf(scootertotalCount));
        lblAvailableScooters.setText(String.valueOf(scooteravailableCount));

        Map<String, Integer> bicycleStatistics = vehicleStatistics.get("Bicycle");
        int bicycletotalCount = bicycleStatistics.get("Count");
        int bicycleavailableCount = bicycleStatistics.get("Available");
        lblAllBicycleCount.setText(String.valueOf(bicycletotalCount));
        lblAvailableBicycle.setText(String.valueOf(bicycleavailableCount));

        Map<String, Integer> carStatistics = vehicleStatistics.get("Car");
        int cartotalCount = carStatistics.get("Count");
        int caravailableCount = carStatistics.get("Available");
        lblAllCarsCount.setText(String.valueOf(cartotalCount));
        lblAvailableCars.setText(String.valueOf(caravailableCount));

        Map<String, Integer> threeWheelStatistics = vehicleStatistics.get("Three Wheel");
        int threeWheeltotalCount = threeWheelStatistics.get("Count");
        int threeWheelavailableCount = threeWheelStatistics.get("Available");
        lblAllThreewheelCount.setText(String.valueOf(threeWheeltotalCount));
        lblAvailableThreeWheels.setText(String.valueOf(threeWheelavailableCount));
    }


    @FXML
    void btnBillingOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/PaymentsManagementForm.fxml"));
        Stage window = (Stage)billingbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Payments Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnBookingOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/BookingPart1Filling.fxml"));
        Stage window = (Stage)bookingbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Booking");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/CustomerManagementForm.fxml"));
        Stage window = (Stage)customerbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Customer Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
        Stage window = (Stage)dashboardbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Dashboard");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnEmployeeManagementOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/EmployeeManagementForm.fxml"));
        Stage window = (Stage)employeebtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Employee Management");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/LogOutPageForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Logout page");
        stage.centerOnScreen();
        stage.show();
        currentStage.close();
    }

    @FXML
    void btnReportOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/reportManageForm.fxml"));
        Stage window = (Stage)reportbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Reports");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnSettingOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/SettingForm.fxml"));
        Stage window = (Stage)settingbtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Setting");
        window.setMaximized(true);
        window.centerOnScreen();
    }

    @FXML
    void btnVehicleOnAction(ActionEvent event) throws IOException {
        Parent fxmlLoader =  FXMLLoader.load(getClass().getResource("/view/VehiclePreviewForm.fxml"));
        Stage window = (Stage)vehiclebtn.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader));
        window.setTitle("Vehicle Preview");
        window.setMaximized(true);
        window.centerOnScreen();
    }
}
