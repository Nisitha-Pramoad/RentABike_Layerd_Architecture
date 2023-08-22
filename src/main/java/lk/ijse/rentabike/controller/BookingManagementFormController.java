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
import lk.ijse.rentabike.bo.custom.BookingManagementBO;
import lk.ijse.rentabike.dto.BookingDTO;
import lk.ijse.rentabike.dto.tm.BookingTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingManagementFormController implements Initializable {

    BookingManagementBO bookingManagementBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.BookingManagement_BO);

    @FXML
    private TableColumn<?, ?> colActon;

    @FXML
    private TableColumn<?, ?> colBikeType;

    @FXML
    private TableColumn<?, ?> colBookingId;

    @FXML
    private TableColumn<?, ?> colBookingStatus;

    @FXML
    private TableColumn<?, ?> colDropoffdate;

    @FXML
    private TableColumn<?, ?> colDropofflocation;

    @FXML
    private TableColumn<?, ?> colDropofftime;

    @FXML
    private TableColumn<?, ?> colPickupdate;

    @FXML
    private TableColumn<?, ?> colPickuplocation;

    @FXML
    private TableColumn<?, ?> colPickuptime;

    @FXML
    private TableView<BookingTm> tblBooking;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        tblBooking.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        tblBooking.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("chooseBike"));
        tblBooking.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("pickUpLocation"));
        tblBooking.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("pickUpDate"));
        tblBooking.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("pickUpTime"));
        tblBooking.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dropOffLocation"));
        tblBooking.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("dropOffDate"));
        tblBooking.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("dropOffTime"));
        tblBooking.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("bookingStatus"));

        tblBooking.getColumns().get(9).setCellValueFactory(param -> {
            ImageView cancel = new ImageView("img/table icons/cancel bike.png");
            HBox hbox = new HBox(20, cancel);
            hbox.setAlignment(Pos.CENTER);

            cancel.setOnMouseClicked(event -> {
                // Handle booked button click
                boolean b = bookingManagementBO.cancelBooking(new BookingDTO(param.getValue().getBookingId()));
                if (!b) {
                    new Alert(Alert.AlertType.ERROR, "something wrong").show();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "booking canceled successfully").show();
                }
                loadAllCustomers();
            });

            return new ReadOnlyObjectWrapper(hbox);
        });
    }


    private void loadAllCustomers() {
        tblBooking.getItems().clear();
        /*Get all customers*/
        try {
            ArrayList<BookingDTO> allBookings = bookingManagementBO.getAllBookings();
            for (BookingDTO b : allBookings) {
                tblBooking.getItems().add(new BookingTm(b.getBookingId(), b.getChooseBike(), b.getPickUpLocation(), b.getPickUpDate(), b.getPickUpTime(), b.getDropOffLocation(), b.getDropOffDate(), b.getDropOffTime(), b.getBookingStatus()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }
}
