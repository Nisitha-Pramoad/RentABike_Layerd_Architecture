package lk.ijse.rentabike.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.rentabike.bo.BoFactory;
import lk.ijse.rentabike.bo.custom.AddEmployeeInformationBO;
import lk.ijse.rentabike.dto.EmployeeDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddEmployeeInformationFormController implements Initializable {
    AddEmployeeInformationBO addEmployeeInformationBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ADDEMPLOYEEINFORMATION_BO);

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtEmployeeTyped;

    @FXML
    private TextField txtNic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtEmployeeId.setText(generateNewId());
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String EmployeeId = txtEmployeeId.getText();
        String EmployeeTyped = txtEmployeeTyped.getText();
        String EmployeeName = txtEmployeeName.getText();
        String Contact = txtContact.getText();
        String Nic = txtNic.getText();
        String Address = txtAddress.getText();
        String Email = txtEmail.getText();

        if (EmployeeId.isEmpty() || EmployeeTyped.isEmpty() || EmployeeName.isEmpty() ||  Contact.isEmpty() ||
                Nic.isEmpty() || Address.isEmpty() || Email.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }else if (!EmployeeName.matches("^[a-zA-Z]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid name").show();
            txtEmployeeName.requestFocus();
            return;
        }else if (!Contact.matches("^\\+?[0-9]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid contact number. Must contain only numbers and an optional '+' sign.").show();
            txtContact.requestFocus();
            return;
        }else if (!Address.matches("^[a-zA-Z0-9\\s.,]{1,50}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid address format.").show();
            txtAddress.requestFocus();
            return;
        }else if (!Email.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid email format.").show();
            txtEmail.requestFocus();
            return;
        }

        boolean b = addEmployeeInformationBO.saveEmployee(new EmployeeDTO(EmployeeId, EmployeeTyped, EmployeeName, Contact, Nic, Address, Email));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "employee has been saved successfully").show();
        }
    }

    private String generateNewId() {
        try {
            return addEmployeeInformationBO.generateNewEmployeeID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String EmployeeId = txtEmployeeId.getText();
        boolean b = addEmployeeInformationBO.deleteEmployee(EmployeeId);
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "employee has been deleted successfully").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String EmployeeId = txtEmployeeId.getText();
        String EmployeeTyped = txtEmployeeTyped.getText();
        String EmployeeName = txtEmployeeName.getText();
        String Contact = txtContact.getText();
        String Nic = txtNic.getText();
        String Address = txtAddress.getText();
        String Email = txtEmail.getText();

        if (EmployeeId.isEmpty() || EmployeeTyped.isEmpty() || EmployeeName.isEmpty() ||  Contact.isEmpty() ||
                Nic.isEmpty() || Address.isEmpty() || Email.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }else if (!EmployeeName.matches("^[a-zA-Z]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid name").show();
            txtEmployeeName.requestFocus();
            return;
        }else if (!Contact.matches("^\\+?[0-9]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid contact number. Must contain only numbers and an optional '+' sign.").show();
            txtContact.requestFocus();
            return;
        }else if (!Address.matches("^[a-zA-Z0-9\\s.,]{1,50}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid address format.").show();
            txtAddress.requestFocus();
            return;
        }else if (!Email.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid email format.").show();
            txtEmail.requestFocus();
            return;
        }

        boolean b = addEmployeeInformationBO.updateEmployee(new EmployeeDTO(EmployeeId, EmployeeTyped, EmployeeName, Contact, Nic, Address, Email));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "employee has been updated successfully").show();
        }
    }

    @FXML
    void codeSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String EmployeeId = txtEmployeeId.getText();

        EmployeeDTO e = addEmployeeInformationBO.searchEmployee(EmployeeId);
        txtEmployeeId.setText(e.getEmployeeId());
        txtEmployeeTyped.setText(e.getEmployeeTyped());
        txtEmployeeName.setText(e.getName());
        txtContact.setText(e.getNic());
        txtNic.setText(e.getAddress());
        txtAddress.setText(e.getContact());
        txtEmail.setText(e.getEmail());
    }


}
