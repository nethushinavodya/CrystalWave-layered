package org.example.controller.AdminControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.DiscountBO;
import org.example.dto.DiscountDTO;
import org.example.dto.tm.DiscountTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DiscountController {

    @FXML
    private TableColumn<?, ?> condition;

    @FXML
    private TextField disCon;

    @FXML
    private TextField disId;

    @FXML
    private TextField disType;

    @FXML
    private TableColumn<?, ?> discountId;

    @FXML
    private TableColumn<?, ?> discountType;

    @FXML
    private TableColumn<?, ?> eDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private TableColumn<?, ?> sDate;

    @FXML
    private DatePicker startDate;

    @FXML
    private TableView<DiscountTM> tableView;

    DiscountBO discountBO = (DiscountBO) BOFactory.getInstance().getBO(BOFactory.BOType.DISCOUNT);
    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValue();
        setAll();
    }

    private void setCellValue() {
        discountId.setCellValueFactory(new PropertyValueFactory<>("discountId"));
        discountType.setCellValueFactory(new PropertyValueFactory<>("discountType"));
        condition.setCellValueFactory(new PropertyValueFactory<>("discountCondition"));
        sDate.setCellValueFactory(new PropertyValueFactory<>("discountStartDate"));
        eDate.setCellValueFactory(new PropertyValueFactory<>("discountEndDate"));
    }
    private void setAll() throws SQLException, ClassNotFoundException {
        ObservableList<DiscountTM> discount = FXCollections.observableArrayList();

        List<DiscountTM> list = discountBO.getAll();

        discount.addAll(list);
        tableView.setItems(discount);
    }
    @FXML
    void handleDelete(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = disId.getText();

        if (isValid()) {
            boolean isDeleted = discountBO.delete(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Discount deleted successfully.").show();
                setAll();
                clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "Discount not deleted successfully.").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Input Valid Data").show();
        }
    }

    @FXML
    void handleSave(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = disId.getText();
        String type = disType.getText();
        int con = Integer.parseInt(disCon.getText());
        String start = String.valueOf(startDate.getValue());
        String end = String.valueOf(endDate.getValue());

        if (isValid()) {

            DiscountDTO discountDTO = new DiscountDTO(id, type, start, end,con);
            boolean isSave = discountBO.save(discountDTO);

            if (isSave) {
                new Alert(Alert.AlertType.CONFIRMATION, "Discount saved successfully").show();
                setAll();
                clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "Discount not saved successfully").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Input Valid Data").show();
        }
    }

    @FXML
    void tableView(MouseEvent event) {
        TableView <DiscountTM> tableView1 = (TableView <DiscountTM>) event.getSource();
        DiscountTM discountTM = tableView1.getSelectionModel().getSelectedItem();
        disId.setText(discountTM.getDiscountId());
        disType.setText(discountTM.getDiscountType());
        disCon.setText(String.valueOf(discountTM.getDiscountCondition()));
        startDate.setValue(LocalDate.parse(discountTM.getDiscountStartDate()));
        endDate.setValue(LocalDate.parse(discountTM.getDiscountEndDate()));
    }
    public void clear(){
        disId.clear();
        disType.clear();
        disCon.clear();
        startDate.setValue(null);
        endDate.setValue(null);

    }

    public void discountTypeOnKeyRelseased(KeyEvent keyEvent) {
        if (disType.getText().matches("[a-zA-Z ]+")){
            disType.setStyle("-fx-border-color: green; -fx-border-width: 2px;; -fx-border-height: 5px;");
        }else {
            disType.setStyle("-fx-border-color: red; -fx-border-width: 2px; ; -fx-border-height: 5px;");
        }
    }

    public void ConditionOnKeyRelease(KeyEvent keyEvent) {
        if (disCon.getText().matches("\\d{1,}")){
            disCon.setStyle("-fx-border-color: green; -fx-border-width: 2px; ; -fx-border-height: 5px;");
        }else {
            disCon.setStyle("-fx-border-color: red; -fx-border-width: 2px; ; -fx-border-height: 5px;");
        }
    }

    public void disIdOnKeyRelease(KeyEvent keyEvent) {
        if(disId.getText().matches("\\d{1,}")){
            disId.setStyle("-fx-border-color: green; -fx-border-width: 2px; ; -fx-border-height: 5px;");
        }else{
            disId.setStyle("-fx-border-color: red; -fx-border-width: 2px; ; -fx-border-height: 5px;");
        }
    }

    public boolean isValid() {
        if (disType.getText().matches("[a-zA-Z ]+")&&
                disCon.getText().matches("\\d{1,}")&&
                     disId.getText().matches("\\d{1,}")
        ) {
            return true;
        }else {
            return false;
        }
    }
    public void backOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminView/AdminDashboard.fxml"));
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
