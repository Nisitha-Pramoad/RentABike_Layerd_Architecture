package lk.ijse.rentabike.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.rentabike.bo.BoFactory;
import lk.ijse.rentabike.bo.custom.CustomerInformationBO;
import lk.ijse.rentabike.dto.CustomerDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerInformationFormController implements Initializable {
    CustomerInformationBO customerInformationBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.CUSTOMERINFORMATION_BO);

    @FXML
    private Button btnAddNewCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFullname;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtZipcode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtCustomerId.setText(generateNewId());
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerId = txtCustomerId.getText();
        String fullName = txtFullname.getText();
        int age = Integer.parseInt(txtAge.getText());
        String Stringage = txtAge.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String country = txtCountry.getText();
        String zipCode = txtZipcode.getText();

        String ageText = txtAge.getText();
        if (ageText.isEmpty()) {
            // Handle the age validation error
            new Alert(Alert.AlertType.ERROR, "Age is required.").show();
            return;
        }
        if (fullName.isEmpty() || phoneNumber.isEmpty() || Stringage.isEmpty() || email.isEmpty() || address.isEmpty() || city.isEmpty() ||
                country.isEmpty() || zipCode.isEmpty() ){
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }else if (!fullName.matches("^[a-zA-Z\\s]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid name").show();
            txtFullname.requestFocus();
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
            txtZipcode.requestFocus();
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

        boolean isCustomerSaved = customerInformationBO.saveCustomer(new CustomerDTO(customerId, fullName, age, phoneNumber, email, address, city, country, zipCode));
        if (isCustomerSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Customer has been saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer has not been saved successfully").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerId = txtCustomerId.getText();
        boolean b = customerInformationBO.deleteCustomer(customerId);
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "customer has been deleted successfully").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerId = txtCustomerId.getText();
        String fullName = txtFullname.getText();
        int age = Integer.parseInt(txtAge.getText());
        String Stringage = txtAge.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String country = txtCountry.getText();
        String zipCode = txtZipcode.getText();

        String ageText = txtAge.getText();
        if (ageText.isEmpty()) {
            // Handle the age validation error
            new Alert(Alert.AlertType.ERROR, "Age is required.").show();
            return;
        }
        if (fullName.isEmpty() || phoneNumber.isEmpty() || Stringage.isEmpty() || email.isEmpty() || address.isEmpty() || city.isEmpty() ||
                country.isEmpty() || zipCode.isEmpty() ){
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }else if (!fullName.matches("^[a-zA-Z\\s]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid name").show();
            txtFullname.requestFocus();
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
            txtZipcode.requestFocus();
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

        boolean isCustomerUpdated = customerInformationBO.updateCustomer(new CustomerDTO(customerId, fullName, age, phoneNumber, email, address, city, country, zipCode));
        if (isCustomerUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Customer has been updated successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer has not been updated successfully").show();
        }
    }

    @FXML
    void CustomerIdSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerId = txtCustomerId.getText();

        ArrayList<CustomerDTO> customerDetails = customerInformationBO.searchCustomer(customerId);
        for (CustomerDTO c : customerDetails) {

            txtCustomerId.setText(c.getCustomerId());
            txtFullname.setText(c.getFullName());
            txtAge.setText(String.valueOf(c.getAge()));
            txtPhoneNumber.setText(c.getPhoneNumber());
            txtEmail.setText(c.getEmail());
            txtAddress.setText(c.getAddress());
            txtCity.setText(c.getCity());
            txtCountry.setText(c.getCountry());
            txtZipcode.setText(c.getZipCode());
        }
    }

    private String generateNewId() {
        try {
            return customerInformationBO.generateNewCustomerID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
