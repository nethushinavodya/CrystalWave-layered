package org.example.controller.ReceptionistControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.CheckInDetailBO;
import org.example.dto.tm.CheckInDetailTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CheckInDetailController {

    @FXML
    private TableColumn<?, ?> GuestId;

    @FXML
    private TableColumn<?, ?> amountColumn;

    @FXML
    private TableColumn<?, ?> checkIn;

    @FXML
    private TableColumn<?, ?> checkOut;

    @FXML
    private TableView<CheckInDetailTM> guestTable;

    @FXML
    private TableColumn<?, ?> paymentStatusColumn;

    @FXML
    private TableColumn<?, ?> reservationId;

    @FXML
    private TableColumn<?, ?> roomId;
    
    CheckInDetailBO checkInDetailBO = (CheckInDetailBO) BOFactory.getInstance().getBO(BOFactory.BOType.CHECKINDETAIL);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValue();
        setCtable();
    }

    private void setCtable() throws SQLException, ClassNotFoundException {
        ObservableList<CheckInDetailTM> data = FXCollections.observableArrayList();

        List<CheckInDetailTM> list = checkInDetailBO.getAll();
        for (CheckInDetailTM checkInDetailTM : list) {
            data.add(checkInDetailTM);
        }
        guestTable.setItems(data);
    }

    private void setCellValue() {
        reservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        GuestId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        roomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        checkIn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        checkOut.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
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
