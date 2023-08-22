package lk.ijse.rentabike.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rentabike.bo.BoFactory;
import lk.ijse.rentabike.bo.custom.BookingFillingBO;
import lk.ijse.rentabike.dto.BookingDTO;
import lk.ijse.rentabike.dto.CustomerDTO;
import lk.ijse.rentabike.dto.PaymentDTO;
import lk.ijse.rentabike.dto.VehicleStatusDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class BookingFillingController implements Initializable {
    BookingFillingBO bookingFillingBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.BOOKINGFilling_BO);

    @FXML
    private Button BookingManagePagebtn;

    @FXML
    private JFXButton billingbtn;

    @FXML
    private JFXButton bookingbtn;

    @FXML
    private JFXComboBox cmbChooseBike;

    @FXML
    private JFXComboBox cmbDropOffLocation;

    @FXML
    private JFXComboBox cmbDropOffTime;

    @FXML
    private JFXComboBox cmbPickUpLocation;

    @FXML
    private JFXComboBox cmbPickUpTime;

    @FXML
    private JFXButton customerbtn;

    @FXML
    private JFXButton dashboardbtn;

    @FXML
    private DatePicker dtpkDropOffLocation;

    @FXML
    private DatePicker dtpkPickUpLocation;

    @FXML
    private JFXButton employeebtn;

    @FXML
    private JFXButton logoutbtn;

    @FXML
    private JFXButton reportbtn;

    @FXML
    private JFXButton settingbtn;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtZipCode;

    @FXML
    private JFXButton vehiclebtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> locationList = FXCollections.observableArrayList(
                "Negombo",
                "Mirissa",
                "Weligama",
                "Ella",
                "Hikkaduwa",
                "Galle",
                "Unawatuna",
                "Kandy",
                "Arugam Bay",
                "Sigiriya",
                "Kalpitiya",
                "Trincomalee",
                "Tangalle",
                "Dickwella",
                "Anuradhapura",
                "Kataragama",
                "Matara",
                "Jaffna",
                "Batticola",
                "Benthota",
                "Nuwara Eliya",
                "Polonnaruwa"
        );
        cmbPickUpLocation.setItems(locationList);
        cmbDropOffLocation.setItems(locationList);

        ObservableList<String> timeList = FXCollections.observableArrayList();
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j += 15) {
                String time = String.format("%02d:%02d", i, j);
                if (i < 12) {
                    time += " am";
                } else if (i == 12) {
                    time += " pm";
                } else {
                    time = String.format("%02d:%02d", i - 12, j) + " pm";
                }
                timeList.add(time);
            }
        }
        cmbPickUpTime.setItems(timeList);
        cmbDropOffTime.setItems(timeList);

        updateAvailableVehicleLIst();
    }

    @FXML
    void btnBooknowOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerId = generateNewId(IDTypes.CUSTOMER_ID);
        String fullName = txtFullName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String Stringage = txtAge.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String country = txtCountry.getText();
        String zipCode = txtZipCode.getText();

        String bookingId = generateNewId(IDTypes.BOOKING_ID);
        String chooseBike = cmbChooseBike.getValue().toString();
        String pickUpLocation = cmbPickUpLocation.getValue().toString();
        Date pickUpDate = Date.valueOf(dtpkPickUpLocation.getValue());
        String pickUpTime = cmbPickUpTime.getValue().toString();
        String dropOffLocation = cmbDropOffLocation.getValue().toString();
        Date dropOffDate = Date.valueOf(dtpkDropOffLocation.getValue());
        String dropOffTime = cmbDropOffTime.getValue().toString();
        String bookingStatus = "booked";

        String paymentId = generateNewId(IDTypes.PAYMENT_ID);
        double bikeRate = getBikeRate(chooseBike);
        LocalDate paymentDate = LocalDate.now();
        String paymentDateString = paymentDate.toString();

        String ageText = txtAge.getText();
        if (ageText.isEmpty()) {
            // Handle the age validation error
            new Alert(Alert.AlertType.ERROR, "Age is required.").show();
            return;
        }

        if (fullName.isEmpty() || phoneNumber.isEmpty() || Stringage.isEmpty() || email.isEmpty() || address.isEmpty() || city.isEmpty() ||
                country.isEmpty() || zipCode.isEmpty() || chooseBike.isEmpty() || pickUpLocation.isEmpty() ||
                pickUpTime.isEmpty() || dropOffLocation.isEmpty() || dropOffTime.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }else if (!fullName.matches("^[a-zA-Z\\s]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid name").show();
            txtFullName.requestFocus();
            return;
        }else if(!phoneNumber.matches("^[+]?[0-9]{1,20}$")){
            new Alert(Alert.AlertType.WARNING, "Invalid phone number").show();
            txtPhoneNumber.requestFocus();
            return;
        }else if (age < 21 || age > 99) {
            new Alert(Alert.AlertType.WARNING, "Invalid age. Must be a number between 21 and 99.").show();
            txtAge.requestFocus();
            return;
        }else if (!email.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            new Alert(Alert.AlertType.WARNING, "Invalid email. Must contain an @ symbol.").show();
            txtEmail.requestFocus();
            return;
        }else if (!zipCode.matches("^[0-9]{1,10}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid zip code").show();
            txtZipCode.requestFocus();
            return;
        }else if (!country.matches("^[a-zA-Z\\s]{1,15}$")){
            new Alert(Alert.AlertType.WARNING, "Invalid country name characters").show();
            txtCountry.requestFocus();
            return;
        }else if (!address.matches("^[a-zA-Z0-9\\s.,]{1,50}$")){
            new Alert(Alert.AlertType.ERROR, "Invalid address format.").show();
            txtAddress.requestFocus();
            return;
        }

        VehicleStatusDTO vehicleStatusDTO = new VehicleStatusDTO();
        vehicleStatusDTO.setName(chooseBike);
        vehicleStatusDTO.setAvailable("booked");
        vehicleStatusDTO.setCustomer_id(customerId);
        vehicleStatusDTO.setBooking_id(bookingId);

        boolean isBookingSaved = bookingFillingBO.saveBooking(new CustomerDTO(customerId, fullName, age, phoneNumber, email, address, city, country, zipCode),
                new BookingDTO(bookingId, chooseBike, pickUpLocation, pickUpDate, pickUpTime, dropOffLocation, dropOffDate, dropOffTime, bookingStatus ),
                vehicleStatusDTO,
                new PaymentDTO(paymentId, bikeRate, "Payment Success!!" , paymentDateString, customerId, bookingId));

        if (isBookingSaved) {
            updateAvailableVehicleLIst();
            clearFields();
            new Alert(Alert.AlertType.INFORMATION, "Booking has been saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Booking has not been saved successfully").show();
        }

    }

    public enum IDTypes {
        CUSTOMER_ID , BOOKING_ID ,PAYMENT_ID,
    }

    private String generateNewId(IDTypes idTypes) {
        try {
            switch (idTypes) {
                case CUSTOMER_ID :
                    return bookingFillingBO.generateNewCustomerID();
                case BOOKING_ID :
                    return bookingFillingBO.generateNewBookingID();
                case PAYMENT_ID :
                    return bookingFillingBO.generateNewPaymentID();

            }
            return bookingFillingBO.generateNewCustomerID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private double getBikeRate(String chooseBike) throws SQLException, ClassNotFoundException {
        return bookingFillingBO.getBikeRate(chooseBike);
    }

    public void updateAvailableVehicleLIst() {
        try {
            List<VehicleStatusDTO> list = bookingFillingBO.searchAvailableVehicle();
            for (VehicleStatusDTO vehicle : list) {
                System.out.println(vehicle.getName());
            }

            ObservableList<String> vList = FXCollections.observableArrayList();
            for (VehicleStatusDTO vehicle : list) {
                String name = vehicle.getName();
                vList.add(name);
            }
            cmbChooseBike.getItems().clear();
            cmbChooseBike.setItems(vList);

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clearFields() {
        txtFullName.clear();
        txtAge.clear();
        txtPhoneNumber.clear();
        txtEmail.clear();
        txtAddress.clear();
        txtCity.clear();
        txtCountry.clear();
        txtZipCode.clear();
        cmbChooseBike.getSelectionModel().clearSelection();
        cmbPickUpLocation.getSelectionModel().clearSelection();
        cmbDropOffLocation.getSelectionModel().clearSelection();
        dtpkPickUpLocation.setValue(null);
        cmbPickUpTime.getSelectionModel().clearSelection();
        cmbDropOffTime.getSelectionModel().clearSelection();
        dtpkDropOffLocation.setValue(null);
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

    @FXML
    void btnBookingManagePageOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/BookingManagementForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Booking Management");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
    }

}
