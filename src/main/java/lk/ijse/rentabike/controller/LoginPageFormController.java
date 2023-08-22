package lk.ijse.rentabike.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rentabike.bo.BoFactory;
import lk.ijse.rentabike.bo.custom.LoginBO;
import lk.ijse.rentabike.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginPageFormController {
    LoginBO loginBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.Login_Bo);

    @FXML
    private Button SignInbtn;

    @FXML
    private Label lblForgotPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    private int loginAttempts = 0;

    @FXML
    void btnSignInToDashOnAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            boolean isUserValid = loginBO.isUserValid(new UserDTO(username, password));

            if (isUserValid) {
                openDashboardForm(actionEvent);
            }else {
                loginAttempts++;
                if (loginAttempts >= 3) {
                    disableLoginButton();
                    new Alert(Alert.AlertType.WARNING, "System Locked. You have entered the wrong password multiple times. Please click on Forgot Password to unlock the system").show();
                } else {
                    showAlert("Invalid Credentials", "Incorrect username or password. Please try again.");
                }
            }

        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void lblForgotPasswordOnMouseClicked(MouseEvent event) {

    }

    private void openDashboardForm(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
        currentStage.close();
    }

    private void disableLoginButton() {
        txtUsername.setDisable(true);
        txtPassword.setDisable(true);
        SignInbtn.setDisable(true);
    }

    private void showAlert(String title, String message) {
        new Alert(Alert.AlertType.ERROR, message).showAndWait();
    }

}
