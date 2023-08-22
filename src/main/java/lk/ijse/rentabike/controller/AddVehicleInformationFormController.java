package lk.ijse.rentabike.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.rentabike.bo.BoFactory;
import lk.ijse.rentabike.bo.custom.AddVehicleInformationBO;
import lk.ijse.rentabike.dto.VehicleDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddVehicleInformationFormController implements Initializable {
    AddVehicleInformationBO addVehicleInformationBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ADDVEHICLEINFORMATION_BO);

    @FXML
    private ComboBox<String> cmbAvailable;

    @FXML
    private ComboBox<String> cmbFirstAidKit;

    @FXML
    private ComboBox<String> cmbMilage;

    @FXML
    private ComboBox<String> cmbRoadAssistance;

    @FXML
    private ComboBox<String> cmbTransmission;

    @FXML
    private TextField txtRent;

    @FXML
    private TextField txtVehicleId;

    @FXML
    private TextField txtVehicleName;

    @FXML
    private TextField txtVehicleType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> milageList = FXCollections.observableArrayList();
        milageList.add("Unlimited");
        milageList.add("limited");
        cmbMilage.setItems(milageList);

        ObservableList<String> FirstAidKitList = FXCollections.observableArrayList();
        FirstAidKitList.add("Yes");
        FirstAidKitList.add("No");
        cmbFirstAidKit.setItems(FirstAidKitList);

        ObservableList<String> TransmissionList = FXCollections.observableArrayList();
        TransmissionList.add("Automatic");
        TransmissionList.add("Manual");
        cmbTransmission.setItems(TransmissionList);

        ObservableList<String> RoadAssistanceList = FXCollections.observableArrayList();
        RoadAssistanceList.add("Yes");
        RoadAssistanceList.add("no");
        cmbRoadAssistance.setItems(RoadAssistanceList);

        ObservableList<String> AvailableList = FXCollections.observableArrayList();
        AvailableList.add("available");
        AvailableList.add("coming soon");
        cmbAvailable.setItems(AvailableList);

        txtVehicleId.setText(generateNewId());
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String VehicleId = txtVehicleId.getText();
        String VehicleName = txtVehicleName.getText();
        String VehicleType = txtVehicleType.getText();
        double Rent = Double.parseDouble(txtRent.getText());
        String Milage = cmbMilage.getValue().toString();
        String FirstAidKit = cmbFirstAidKit.getValue().toString();
        String Transmission = cmbTransmission.getValue().toString();
        String RoadAssistance = cmbRoadAssistance.getValue().toString();
        String Available = cmbAvailable.getValue().toString();

        if (VehicleId.isEmpty() || VehicleName.isEmpty() || VehicleType.isEmpty() ||
                Milage.isEmpty() || FirstAidKit.isEmpty() || Transmission.isEmpty() ||
                RoadAssistance.isEmpty() || Available.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        } else if (!VehicleName.matches("^[a-zA-Z]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid vehicle name").show();
            txtVehicleName.requestFocus();
            return;
        } else if (!Milage.matches("^[0-9]+$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid mileage. Must contain only numbers.").show();
            // Adjust the appropriate field and focus based on your requirements
            return;
        } else if (!String.valueOf(Rent).matches("^\\d+(\\.\\d+)?$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid rent. Please enter a valid number!").show();
            // Adjust the appropriate field and focus based on your requirements
            return;
        }



        boolean b = addVehicleInformationBO.saveNewVehicle(new VehicleDTO(VehicleId, VehicleName, VehicleType, Rent, Milage, FirstAidKit, Transmission, RoadAssistance, Available));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "vehicle has been saved successfully").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String vehicleId = txtVehicleId.getText();
        boolean b = addVehicleInformationBO.deleteVehicle(vehicleId);
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "vehicle has been deleted successfully").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String VehicleId = txtVehicleId.getText();
        String VehicleName = txtVehicleName.getText();
        String VehicleType = txtVehicleType.getText();
        double Rent = Double.parseDouble(txtRent.getText());
        String Milage = cmbMilage.getValue().toString();
        String FirstAidKit = cmbFirstAidKit.getValue().toString();
        String Transmission = cmbTransmission.getValue().toString();
        String RoadAssistance = cmbRoadAssistance.getValue().toString();
        String Available = cmbAvailable.getValue().toString();

        boolean b = addVehicleInformationBO.updateVehicle(new VehicleDTO(VehicleId, VehicleName, VehicleType, Rent, Milage, FirstAidKit, Transmission, RoadAssistance, Available));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "vehicle has been updated successfully").show();
        }
    }

    @FXML
    void codeSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String VehicleId = txtVehicleId.getText();

        ArrayList<VehicleDTO> vehicleDetails = addVehicleInformationBO.searchVehicle(VehicleId);
        for (VehicleDTO v : vehicleDetails) {

            txtVehicleId.setText(v.getVehicleId());
            txtVehicleName.setText(v.getVehicleName());
            txtVehicleType.setText(v.getType());
            txtRent.setText(String.valueOf(v.getRent()));
            cmbMilage.setValue(v.getMilage());
            cmbFirstAidKit.setValue(v.getFirstAidKit());
            cmbTransmission.setValue(v.getTransmission());
            cmbRoadAssistance.setValue(String.valueOf(v.getRoadAssistance()));
            cmbAvailable.setValue(v.getAvailable());
        }
    }

    private String generateNewId() {
        try {
            return addVehicleInformationBO.generateNewVehicleID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
