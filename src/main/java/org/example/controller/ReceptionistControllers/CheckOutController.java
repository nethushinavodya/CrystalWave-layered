package org.example.controller.ReceptionistControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import com.example.layeredarchitecture.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.example.bo.custom.RoomBO;
import org.example.dao.DAOFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.layeredarchitecture.db.DBConnection.*;

public class CheckOutController {

    public ComboBox roomIdCmb;
    public TextField reservation_id;
    @FXML
    private Button checkoutButton;
    @FXML
    private Button generateBillButton;
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOM);
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        selectRoomIdCmb();
    }

    private void selectRoomIdCmb() throws SQLException, ClassNotFoundException {
        ObservableList<String> room = FXCollections.observableArrayList();
        List<String > roomId = roomBO.getDeactiveRooms();

        room.addAll(roomId);

        roomIdCmb.setItems(room);
    }

    @FXML
    void handleCheckout(ActionEvent event) throws SQLException, ClassNotFoundException {
        String roomId = (String) roomIdCmb.getValue();

        boolean isCheckout = roomBO.checkOut(roomId);
        if (isCheckout) {
            new Alert(Alert.AlertType.INFORMATION, "Checkout Successful").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Checkout Failed").show();
        }

    }

    @FXML
    void handleGenerateBill(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/nethushi.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("rid", reservation_id.getText());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DBConnection.getDbConnection().getConnection());
        JasperViewer.viewReport(jasperPrint, false);

    }

    public void roomIDCmb(ActionEvent actionEvent) {
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
