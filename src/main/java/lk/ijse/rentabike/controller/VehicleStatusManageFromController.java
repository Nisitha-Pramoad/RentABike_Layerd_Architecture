package lk.ijse.rentabike.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import lk.ijse.rentabike.bo.BoFactory;
import lk.ijse.rentabike.bo.custom.VehicleStatusManageBO;
import lk.ijse.rentabike.dto.BookingDTO;
import lk.ijse.rentabike.dto.VehicleStatusDTO;
import lk.ijse.rentabike.dto.tm.BookingTm;
import lk.ijse.rentabike.dto.tm.VehicleStatusTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VehicleStatusManageFromController implements Initializable {
    VehicleStatusManageBO vehicleStatusManageBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.VEHICLESTATUSMANAGE_BO);

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colRentPrice;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private TableColumn<?, ?> colVehicleName;

    @FXML
    private TableColumn<?, ?> colavailable;

    @FXML
    private TableColumn<?, ?> colbookingid;

    @FXML
    private TableView<VehicleStatusTm> tblVehicleStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        tblVehicleStatus.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        tblVehicleStatus.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblVehicleStatus.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblVehicleStatus.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
        tblVehicleStatus.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblVehicleStatus.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("bookingdId"));
        tblVehicleStatus.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("available"));

        tblVehicleStatus.getColumns().get(7).setCellValueFactory(param -> {
            ImageView booked = new ImageView("img/table icons/return bike.png");
            HBox hbox = new HBox(20, booked);
            hbox.setAlignment(Pos.CENTER);

            VehicleStatusDTO vehicleStatusDTO = new VehicleStatusDTO();
            vehicleStatusDTO.setBooking_id(param.getValue().getBookingdId());
            booked.setOnMouseClicked(event -> {
                // Handle booked button click
                boolean b = vehicleStatusManageBO.vehicleReturnManage(vehicleStatusDTO);
                if (!b) {
                    new Alert(Alert.AlertType.ERROR, "something wrong").show();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "vehicle returned successfully").show();
                }
                loadAllCustomers();
            });

            return new ReadOnlyObjectWrapper(hbox);
        });
    }

    private void loadAllCustomers() {
        tblVehicleStatus.getItems().clear();
        /*Get all customers*/
        try {
            ArrayList<VehicleStatusDTO> allBookings = vehicleStatusManageBO.getAllVehicles();
            for (VehicleStatusDTO v : allBookings) {
                tblVehicleStatus.getItems().add(new VehicleStatusTm(v.getVehicleId(), v.getName(), v.getType(), v.getRent_price(), v.getCustomer_id(), v.getBooking_id(), v.getAvailable()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


}
