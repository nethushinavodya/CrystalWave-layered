package org.example.controller.HomeControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.LoginBO;
import org.example.dto.UserDTO;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class SignUpController {

    @FXML private TextField passwordField;
    @FXML private TextField confirmPasswordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField addressField;
    @FXML private TextField emailField;
    @FXML private ComboBox<String> roleComboBox;

    LoginBO loginBO = (LoginBO) BOFactory.getInstance().getBO(BOFactory.BOType.LOGIN);
    @FXML
    private void handleSignUp() throws ClassNotFoundException, SQLException {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String address = addressField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();
        String role = roleComboBox.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() ||
                email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || role == null) {
            showAlert(AlertType.WARNING, "Form Error", "Please fill in all fields.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            showAlert(AlertType.WARNING, "Password Error", "Passwords do not match.");
            return;
        }
        if (!isValidEmail(email)) {
            showAlert(AlertType.WARNING, "Email Error", "Please enter a valid email address.");
            return;
        }

        try {
            if (loginBO.getUserByEmailAndRole(email) != null) {
                showAlert(AlertType.WARNING, "Email Exists", "This email is already registered.");
                return;
            }
        } catch (SQLException e) {
            showAlert(AlertType.ERROR, "Database Error", "Error checking email in database.");
            e.printStackTrace();
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        UserDTO userdto = new UserDTO(firstName, lastName, phoneNumber, address, email, hashedPassword, role);

        if (loginBO.saveUser(userdto)) {
            showAlert(AlertType.INFORMATION, "Sign Up Successful", "You have successfully signed up!");
            navigateToLogin();
        } else {
            showAlert(AlertType.ERROR, "Sign Up Failed", "An error occurred while signing up.");
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void navigateToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeView/LoginPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) firstNameField.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Navigation Error", "Failed to load the Login page.");
            e.printStackTrace();
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    public void fNameOnKeyRelease(KeyEvent keyEvent) {
        if(firstNameField.getText().matches("^[A-z|\\\\s]{4,}$")) {
            firstNameField.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }else {
            firstNameField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }
    }

    public void lNameOnKeyRelease(KeyEvent keyEvent) {
        if(lastNameField.getText().matches("^[A-z|\\\\s]{4,}$")) {
            lastNameField.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }else {
            lastNameField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }
    }

    public void contactOnKeyRelease(KeyEvent keyEvent) {
       if(phoneNumberField.getText().matches("^\\d{3}[- ]?\\d{3}[- ]?\\d{4}$")){
           phoneNumberField.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 5px;");
       }else {
           phoneNumberField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
       }
    }

    public void addressOnKeyRelease(KeyEvent keyEvent) {
        if (addressField.getText().matches("^[a-zA-Z0-9\\s,.'#-]{5,100}$")) {
            addressField.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }else {
            addressField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }
    }

    public void emailOnKeyRelease(KeyEvent keyEvent) {
        if (emailField.getText().matches("^[\\w!#$%&'*+/=?{|}~^-]+(?:\\.[\\w!#$%&'*+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
            emailField.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }else {
            emailField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }
    }
}
