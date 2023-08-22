package lk.ijse.rentabike.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.rentabike.bo.BoFactory;
import lk.ijse.rentabike.bo.custom.SalaryManagementBO;
import lk.ijse.rentabike.dto.EmployeeDTO;
import lk.ijse.rentabike.dto.SalaryDTO;
import lk.ijse.rentabike.dto.tm.SalaryTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SalaryManagementFormController implements Initializable {
    SalaryManagementBO salaryManagementBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.SALARYMANAGEMENT_BO);

    @FXML
    private ComboBox<String> cmbEmployeeId;

    @FXML
    private ComboBox<String> cmbMonth;

    @FXML
    private ComboBox<String> cmbSalaryType;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colMonth;

    @FXML
    private TableColumn<?, ?> colSalaryId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<SalaryTm> tblSalary;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtSalaryId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> salaryTypeList = FXCollections.observableArrayList(
                "Basic",
                "Allowances",
                "Overtime Pay",
                "Bonuses",
                "Commissions"
        );
        cmbSalaryType.setItems(salaryTypeList);

        ObservableList<String> monthList = FXCollections.observableArrayList(
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        );
        cmbMonth.setItems(monthList);

        setCellValueFactory();
        loadALlEmployees();
        loadAllSalaries();
        txtSalaryId.setText(generateNewId());
    }

    void loadALlEmployees(){
        try {
            List<EmployeeDTO> allEmployees = salaryManagementBO.getAllEmployees();

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

    private void setCellValueFactory() {
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
    }

    void loadAllSalaries(){
        tblSalary.getItems().clear();

        try {
            ArrayList<SalaryDTO> allSalaries = salaryManagementBO.getAllSalaries();
            for (SalaryDTO s : allSalaries) {
                tblSalary.getItems().add(new SalaryTm(s.getSalaryId(), s.getDescription(), s.getAmount(), s.getType(), s.getMonth(), s.getEmployeeId()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String SalaryId = txtSalaryId.getText();
        String description = txtDescription.getText();
        double Amount = Double.parseDouble(txtAmount.getText());
        String type = cmbSalaryType.getValue();
        String month = cmbMonth.getValue();
        String employeeId = cmbEmployeeId.getValue();

        //regex

        boolean b = salaryManagementBO.saveSalary(new SalaryDTO(SalaryId, description, Amount, type, month, employeeId));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadAllSalaries();
            new Alert(Alert.AlertType.CONFIRMATION, "salary has been saved successfully").show();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String salaryId = txtSalaryId.getText();

        boolean b = salaryManagementBO.deleteSalary(salaryId);
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadAllSalaries();
            new Alert(Alert.AlertType.CONFIRMATION, "salary has been deleted successfully").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String SalaryId = txtSalaryId.getText();
        String description = txtDescription.getText();
        double Amount = Double.parseDouble(txtAmount.getText());
        String type = cmbSalaryType.getValue();
        String month = cmbMonth.getValue();
        String employeeId = cmbEmployeeId.getValue();

        boolean b = salaryManagementBO.updateSalary(new SalaryDTO(SalaryId, description, Amount, type, month, employeeId));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadAllSalaries();
            new Alert(Alert.AlertType.CONFIRMATION, "salary has been updated successfully").show();
        }
    }

    @FXML
    void codeSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String salaryId = txtSalaryId.getText();

        SalaryDTO s = salaryManagementBO.searchSalary(salaryId);

        txtSalaryId.setText(s.getSalaryId());
        txtDescription.setText(s.getDescription());
        txtAmount.setText(String.valueOf(s.getAmount()));
        cmbSalaryType.setValue(s.getType());
        cmbMonth.setValue(s.getMonth());
        cmbEmployeeId.setValue(s.getEmployeeId());

    }

    private String generateNewId() {
        try {
            return salaryManagementBO.generateNewSalaryID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
