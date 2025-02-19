package org.example.controller.ReceptionistControllers;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.GuestBO;
import org.example.dto.AddGuestDTO;
import org.example.dto.tm.AddGuestTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddGuestController {
    public TextField Id;
    public TextField name;
    public TextField contactNo;
    public TextField email;
    public TextField address;
    public TableView <AddGuestTM>tableView;
    public TableColumn <?,?>guestId;
    public TableColumn <?,?>guestName;
    public TableColumn <?,?>guestContactNo;
    public TableColumn <?,?>guestAddress;
    public TableColumn <?,?>guestEmail;

    GuestBO guestBO = (GuestBO) BOFactory.getInstance().getBO(BOFactory.BOType.GUEST);
    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        setCellvalue();
        setAll();
        getCurrentGuestId();
        Id.setDisable(true);
    }

    private void getCurrentGuestId() throws SQLException, ClassNotFoundException {
        String currentGuestId = guestBO.getCurrentGuestId();

        String nextGuestId = generateNextGuestId(currentGuestId);
        Id.setText(nextGuestId);

    }

    private String generateNextGuestId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("G");
            int idNum = Integer.parseInt(split[1]);
            return "G" + ++idNum;
        }
        return "G1";
    }

    private void setCellvalue() {
        guestId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        guestName.setCellValueFactory(new PropertyValueFactory<>("guestName"));
        guestContactNo.setCellValueFactory(new PropertyValueFactory<>("guestPhone"));
        guestAddress.setCellValueFactory(new PropertyValueFactory<>("guestAddress"));
        guestEmail.setCellValueFactory(new PropertyValueFactory<>("guestEmail"));

    }

    private void setAll() throws SQLException, ClassNotFoundException {
        ObservableList<AddGuestTM> guestTMS = FXCollections.observableArrayList();

        List<AddGuestTM> list = guestBO.getAll();

        for (AddGuestTM guestTM: list) {
            guestTMS.add(guestTM);
        }
        tableView.setItems(guestTMS);

    }
    public void handleSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = Id.getText();
        String guestName = name.getText();
        String guestContactNo = contactNo.getText();
        String guestAddress = address.getText();
        String guestEmail = email.getText();

        if (isValid()) {
            AddGuestDTO addGuestDTO = new AddGuestDTO(id, guestName, guestContactNo, guestAddress, guestEmail);
            boolean isSave = guestBO.save(addGuestDTO);

            if (isSave) {
                new Alert(Alert.AlertType.INFORMATION, "Guest added successfully").show();
                setAll();
                getCurrentGuestId();
                clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "Guest not added successfully").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Inout Valid Data").show();
        }
    }

    public void handleUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = Id.getText();
        String guestName = name.getText();
        String guestContactNo = contactNo.getText();
        String guestAddress = address.getText();
        String guestEmail = email.getText();

        if (isValid()) {
            AddGuestDTO addGuestDTO = new AddGuestDTO(id, guestName, guestContactNo, guestAddress, guestEmail);
            boolean isUpdate = guestBO.update(addGuestDTO);

            if (isUpdate) {
                new Alert(Alert.AlertType.INFORMATION, "Guest updated successfully").show();
                setAll();
                clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "Guest not updated successfully").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Inout Valid Data").show();
        }
    }

    public void handleDelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = Id.getText();

        boolean isDelete = guestBO.delete(id);
        if(isDelete){
            new Alert(Alert.AlertType.INFORMATION, "Guest deleted successfully").show();
            setAll();
            clear();
        }else {
            new Alert(Alert.AlertType.ERROR, "Guest not deleted successfully").show();
        }
    }

    public void tableView(MouseEvent mouseEvent) {
        TableView <AddGuestTM>tableView = (TableView<AddGuestTM>) mouseEvent.getSource();
        AddGuestTM addGuestTM = tableView.getSelectionModel().getSelectedItem();
        Id.setText(addGuestTM.getGuestId());
        name.setText(addGuestTM.getGuestName());
        contactNo.setText(addGuestTM.getGuestPhone());
        address.setText(addGuestTM.getGuestAddress());
        email.setText(addGuestTM.getGuestEmail());
    }

    public void nameOnKeyRelease(KeyEvent keyEvent) {
        if (name.getText().matches("[a-zA-Z ]+")) {
            name.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-height: 5px;");
        }else {
            name.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-height: 5px;");
        }
    }

    public void contactOnKeyRelease(KeyEvent keyEvent) {
        if (contactNo.getText().matches("\\d{10}$")) {
            contactNo.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-height: 5px;");
        }else {
            contactNo.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-height: 5px;");
        }
    }

    public void addressOnKeyRelease(KeyEvent keyEvent) {
        if (address.getText().matches("^[a-zA-Z0-9\\s,.'#-]{5,100}$")) {
            address.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-height: 5px;");
        }else{
            address.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-height: 5px;");
        }
    }

    public void emailOnKeyRelease(KeyEvent keyEvent) {
        if (email.getText().matches("^[\\w!#$%&'*+/=?{|}~^-]+(?:\\.[\\w!#$%&'*+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            email.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-height: 5px;");
        }else {
            email.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-height: 5px;");
        }
    }
    public boolean isValid() {
        if (name.getText().matches("[a-zA-Z ]+")&&
                contactNo.getText().matches("\\d{10}$") &&
                address.getText().matches("^[a-zA-Z0-9\\s,.'#-]{5,100}$") &&
                email.getText().matches("^[\\w!#$%&'*+/=?{|}~^-]+(?:\\.[\\w!#$%&'*+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
        ){
            return true;
        }else {
            return false;
        }
    }
    public void clear(){
        name.clear();
        contactNo.clear();
        address.clear();
        email.clear();

    }

    public void backOnAction(ActionEvent actionEvent) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReceptionistView/ReceptionistDashboard.fxml"));
                Parent root = loader.load();

                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
