package lk.ijse.rentabike.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.rentabike.bo.BoFactory;
import lk.ijse.rentabike.bo.custom.AttendenceManagementBO;
import lk.ijse.rentabike.dto.AttendenceDTO;
import lk.ijse.rentabike.dto.EmployeeDTO;
import lk.ijse.rentabike.dto.tm.AttendenceTm;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AttendenceManagementFormController implements Initializable {
    AttendenceManagementBO attendenceManagementBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ATTENDENCEMANAGEMENT_BO);

    @FXML
    private ComboBox<String> cmbEmployeeId;

    @FXML
    private ComboBox<String> cmbHoliday;

    @FXML
    private TableColumn<?, ?> colAttendencedId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colHoliday;

    @FXML
    private TableColumn<?, ?> colSignInTime;

    @FXML
    private TableColumn<?, ?> colSignOutTime;

    @FXML
    private DatePicker dtpkDate;

    @FXML
    private TableView<AttendenceTm> tblAttendenceManagement;

    @FXML
    private TextField txtAttendenceId;

    @FXML
    private TextField txtSignInTime;

    @FXML
    private TextField txtSignOutTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> dayStatusList = FXCollections.observableArrayList();
        dayStatusList.add("workday");
        dayStatusList.add("holiday");
        cmbHoliday.setItems(dayStatusList);

        txtAttendenceId.setText(generateNewId());

        setCellValueFactory();
        loadAllAttendence();
        loadALlEmployees();
    }

    private void setCellValueFactory() {
        colAttendencedId.setCellValueFactory(new PropertyValueFactory<>("attendenceId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colHoliday.setCellValueFactory(new PropertyValueFactory<>("holiday"));
        colSignInTime.setCellValueFactory(new PropertyValueFactory<>("signInTime"));
        colSignOutTime.setCellValueFactory(new PropertyValueFactory<>("signOutTime"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
    }

    void loadAllAttendence(){
        tblAttendenceManagement.getItems().clear();

        try {
            ArrayList<AttendenceDTO> allAttendence = attendenceManagementBO.getAllAttendence();
            for (AttendenceDTO c : allAttendence) {
                tblAttendenceManagement.getItems().add(new AttendenceTm(c.getAttendenceId(), c.getDate(), c.getHoliday(), c.getSignInTime(), c.getSignOutTime(), c.getEmployeeId()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    void loadALlEmployees(){
        try {
            List<EmployeeDTO> allEmployees = attendenceManagementBO.getAllEmployees();

            ObservableList<String> vList = FXCollections.observableArrayList();
            for (EmployeeDTO employeeDTO : allEmployees) {
                String name = employeeDTO.getEmployeeId();
                vList.add(name);
            }
            cmbEmployeeId.getItems().clear();
            cmbEmployeeId.setItems(vList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private String generateNewId() {
        try {
            return attendenceManagementBO.generateNewAttendenceID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String AttendenceId = txtAttendenceId.getText();
        LocalDate date = dtpkDate.getValue();
        String Holiday = cmbHoliday.getValue().toString();
        String SignInTime = txtSignInTime.getText();
        String SignOutTime = txtSignOutTime.getText();
        String EmployeedId = cmbEmployeeId.getValue().toString();

        if (AttendenceId.isEmpty() || date == null || Holiday.isEmpty() || SignInTime.isEmpty() || SignOutTime.isEmpty() || EmployeedId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }else if (!SignInTime.matches("([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d")) {
            new Alert(Alert.AlertType.WARNING,"Invalid time. Must be in the format HH:mm:ss.").show();
            txtSignInTime.requestFocus();
            return;
        }else if(!SignOutTime.matches("([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d")){
            new Alert(Alert.AlertType.WARNING,"Invalid time. Must be in the format HH:mm:ss.").show();
            txtSignOutTime.requestFocus();
            return;
        }

        boolean b = attendenceManagementBO.saveAttendence(new AttendenceDTO(AttendenceId, date, Holiday, SignInTime, SignOutTime, EmployeedId));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadAllAttendence();
            new Alert(Alert.AlertType.CONFIRMATION, "attendence has been saved successfully").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String attendenceId = txtAttendenceId.getText();

        boolean b = attendenceManagementBO.deleteAttendence(attendenceId);
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadAllAttendence();
            new Alert(Alert.AlertType.CONFIRMATION, "attendence has been deleted successfully").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String AttendenceId = txtAttendenceId.getText();
        LocalDate date = dtpkDate.getValue();
        String Holiday = cmbHoliday.getValue().toString();
        String SignInTime = txtSignInTime.getText();
        String SignOutTime = txtSignOutTime.getText();
        String EmployeedId = cmbEmployeeId.getValue().toString();

        if (AttendenceId.isEmpty() || date == null || Holiday.isEmpty() || SignInTime.isEmpty() || SignOutTime.isEmpty() || EmployeedId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }else if (!SignInTime.matches("([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d")) {
            new Alert(Alert.AlertType.WARNING,"Invalid time. Must be in the format HH:mm:ss.").show();
            txtSignInTime.requestFocus();
            return;
        }else if(!SignOutTime.matches("([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d")){
            new Alert(Alert.AlertType.WARNING,"Invalid time. Must be in the format HH:mm:ss.").show();
            txtSignOutTime.requestFocus();
            return;
        }

        boolean b = attendenceManagementBO.updateAttendence(new AttendenceDTO(AttendenceId, date, Holiday, SignInTime, SignOutTime, EmployeedId));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadAllAttendence();
            new Alert(Alert.AlertType.CONFIRMATION, "vehicle has been updated successfully").show();
        }
    }

    @FXML
    void codeSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String AttendenceId = txtAttendenceId.getText();

        ArrayList<AttendenceDTO> attendenceDetails = attendenceManagementBO.searchAttendence(AttendenceId);
        for (AttendenceDTO a : attendenceDetails) {

            txtAttendenceId.setText(a.getAttendenceId());
            String dateString = a.getDate();
            LocalDate localDate = LocalDate.parse(dateString);
            dtpkDate.setValue(localDate);
            cmbHoliday.setValue(a.getHoliday());
            txtSignInTime.setText(a.getSignInTime());
            txtSignOutTime.setText(a.getSignOutTime());
            cmbEmployeeId.setValue(a.getEmployeeId());
        }
    }
}
