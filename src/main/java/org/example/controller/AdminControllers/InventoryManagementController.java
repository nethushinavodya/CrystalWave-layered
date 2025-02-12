package org.example.controller.AdminControllers;

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
import org.example.bo.custom.InventoryBO;
import org.example.dto.InventoryDTO;
import org.example.dto.tm.InventoryTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class InventoryManagementController {

    public TableColumn itemIdColumn;
    public TableColumn itemNameColumn;
    public TableColumn quantityColumn;
    public TableColumn roomTypeIdColumn;
    public TableColumn priceColumn;
    public TextField itemIdField;
    public TextField quantityField;
    public TextField priceField;
    @FXML
    private TextField itemNameField;
    @FXML
    private TableView<InventoryTm> inventoryTable;

    InventoryBO inventoryBO = (InventoryBO) BOFactory.getInstance().getBO(BOFactory.BOType.INVENTORY);
    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        setCellvalue();
        setAll();
        getCurrentItemId();
        itemIdField.setDisable(true);
    }

    private void getCurrentItemId() throws SQLException, ClassNotFoundException {
        String currentItemId = inventoryBO.getCurrentItemId();

        String nextItemId = generateNextInvId(currentItemId);
        itemIdField.setText(nextItemId);

    }

    private String generateNextInvId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("C");
            int idNum = Integer.parseInt(split[1]);
            return "C" + ++idNum;
        }
        return "C1";
    }

    private void setCellvalue() {
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        roomTypeIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
    }

    private void setAll() throws SQLException, ClassNotFoundException {
        ObservableList<InventoryTm> inventoryTypeTms = FXCollections.observableArrayList();

        List<InventoryTm> list = inventoryBO.getAll();

        for (InventoryTm InventoryTm : list) {
            inventoryTypeTms.add(InventoryTm);
        }
        inventoryTable.setItems(inventoryTypeTms);

    }
    public void handleDeleteItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        try {
            String itemId = itemIdField.getText();
            boolean isDeleted = inventoryBO.delete(itemId);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item deleted successfully.").show();
                setAll();
                clear();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Item not deleted successfully.").show();
            }
        }catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    public void handleUpdateItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemId = itemIdField.getText();
        String itemName = itemNameField.getText();
        String itemQuantity = quantityField.getText();
        String itemPrice = priceField.getText();
        if (isValid()) {
            try {
                InventoryDTO inventoryDTO = new InventoryDTO(itemId, itemName, itemQuantity, itemPrice);
                boolean isUpdate = inventoryBO.update(inventoryDTO);

                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item updated successfully").show();
                    setAll();
                    clear();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Item not updated successfully").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Input Valid Data").show();
        }
    }

    public void handleAddItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemId = itemIdField.getText();
        String itemName = itemNameField.getText();
        String itemQuantity = quantityField.getText();
        String itemPrice = priceField.getText();

        if (isValid()) {
            try {
                InventoryDTO inventoryDTO = new InventoryDTO(itemId, itemName, itemQuantity, itemPrice);
                boolean isSave = inventoryBO.save(inventoryDTO);

                if (isSave) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item added successfully").show();
                    setAll();
                    getCurrentItemId();
                    clear();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Item not added successfully").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Input Valid Data").show();
        }

    }

    public void inventoryTable (MouseEvent mouseEvent) {
        TableView <InventoryTm> tableView = (TableView <InventoryTm>) inventoryTable;
        InventoryTm inventoryTm = tableView.getSelectionModel().getSelectedItem();
        itemIdField.setText(String.valueOf(inventoryTm.getItemId()));
        itemNameField.setText(inventoryTm.getItemName());
        quantityField.setText(String.valueOf(inventoryTm.getItemQuantity()));
        priceField.setText(String.valueOf(inventoryTm.getItemPrice()));

    }
    public void clear(){
        itemNameField.clear();
        quantityField.clear();
        priceField.clear();


    }

    public void handleClear(ActionEvent actionEvent) {
        clear();
    }

    public void iNameOnKeyRe(KeyEvent keyEvent) {
        if (itemNameField.getText().matches("[a-zA-Z ]+")) {
            itemNameField.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-height: 5px");
        }else {
            itemNameField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-height: 5px");
        }
    }

    public void quantityOnKeyRelease(KeyEvent keyEvent) {
        if(quantityField.getText().matches("\\d{1,}")) {
            quantityField.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-height: 5px");
        }else {
            quantityField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-height: 5px");
        }
    }

    public void priceOnKeyRelease(KeyEvent keyEvent) {
        if(priceField.getText().matches("\\d{3,}")) {
            priceField.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-height: 5px");
        }else {
            priceField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-height: 5px");
        }
    }
    public boolean isValid(){
        if (itemNameField.getText().matches("[a-zA-Z ]+")&&
                quantityField.getText().matches("\\d{1,}")&&
                priceField.getText().matches("\\d{3,}")
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
