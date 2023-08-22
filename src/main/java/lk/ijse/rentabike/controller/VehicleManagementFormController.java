package lk.ijse.rentabike.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rentabike.bo.BoFactory;
import lk.ijse.rentabike.bo.custom.VehicleManageBO;
import lk.ijse.rentabike.dto.VehicleDTO;
import lk.ijse.rentabike.dto.tm.VehicleTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VehicleManagementFormController implements Initializable {
    VehicleManageBO vehicleManageBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.VEHICLE_MANAGE);

    @FXML
    private TableColumn<?, ?> colAvailable;

    @FXML
    private TableColumn<?, ?> colFirstAidKit;

    @FXML
    private TableColumn<?, ?> colMileage;

    @FXML
    private TableColumn<?, ?> colRentPrice;

    @FXML
    private TableColumn<?, ?> colRoadsideAssistance;

    @FXML
    private TableColumn<?, ?> colTransmission;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private TableColumn<?, ?> colVehicleName;

    @FXML
    private TableView<VehicleTm> tblVehicleManagement;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        loadAllVehicles();
    }

    private void setCellValueFactory() {
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        colVehicleName.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));
        colType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colRentPrice.setCellValueFactory(new PropertyValueFactory<>("vehicleRent"));
        colMileage.setCellValueFactory(new PropertyValueFactory<>("vehicleMilage"));
        colFirstAidKit.setCellValueFactory(new PropertyValueFactory<>("vehicleFirstAidKit"));
        colTransmission.setCellValueFactory(new PropertyValueFactory<>("vehicleTransmission"));
        colRoadsideAssistance.setCellValueFactory(new PropertyValueFactory<>("vehicleRoadAssistance"));
        colAvailable.setCellValueFactory(new PropertyValueFactory<>("vehicleAvailale"));
    }

    private void loadAllVehicles(){
        tblVehicleManagement.getItems().clear();
        /*Get all customers*/
        try {
            ArrayList<VehicleDTO> allVehicle = vehicleManageBO.getALlVehicles();
            for (VehicleDTO v : allVehicle) {
                tblVehicleManagement.getItems().add(new VehicleTm(v.getVehicleId(), v.getVehicleName(), v.getType(), v.getRent(), v.getMilage(), v.getFirstAidKit(), v.getTransmission(), v.getRoadAssistance(), v.getAvailable()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnVehicleManagementOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/AddVehicleForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Add Vehicle");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
    }
}
