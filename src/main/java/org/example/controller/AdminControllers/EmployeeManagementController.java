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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.EmployeeBO;
import org.example.dto.EmployeeDTO;
import org.example.dto.tm.EmployeeTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeManagementController {

    public TextField eIdtxt;
    public TextField nameField;
    public TextField roleField;
    public TextField contactField;
    public TableColumn <?,?> idclm;
    public TableColumn <?,?> employeeNameColumn;
    public TableColumn <?,?> roleColumn;
    public TableColumn <?,?> contactNoColumn;
    public AnchorPane employeeContent;
    @FXML
    private TableView<EmployeeTm> employeeTable;
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);
    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        setCellValue();
        setAll();
        getCurrentEId();
        eIdtxt.setDisable(true);
    }

    private void getCurrentEId() throws ClassNotFoundException {
        try {
            String currentEId = employeeBO.getCurrentEmployeeId();

            String nextEmployeeId = generateNextEmployeeId(currentEId);
            eIdtxt.setText(nextEmployeeId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextEmployeeId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("E");
            int idNum = Integer.parseInt(split[1]);
            return "E" + ++idNum;
        }
        return "E1";
    }



    private void setAll() throws SQLException, ClassNotFoundException {
        ObservableList<EmployeeTm> employees = FXCollections.observableArrayList();

        List<EmployeeDTO> list = employeeBO.getAll();

        for (EmployeeDTO employee : list) {
            String id = String.valueOf(employee.getEmployeeId());
            EmployeeTm employeeTm = new EmployeeTm(id,employee.getName(),employee.getRole(),employee.getContact());
            employees.add(employeeTm);
        }
        employeeTable.setItems(employees);

    }

    private void setCellValue() {
        idclm.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        contactNoColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void handleUpdateEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String eId = eIdtxt.getText();
        String employeeName = nameField.getText();
        String employeeRole = roleField.getText();
        String contactFieldText =contactField.getText();

        if(isValid()) {
            try {
                EmployeeDTO employeeDTO = new EmployeeDTO(eId, employeeName, employeeRole, contactFieldText);
                boolean isUpdate = employeeBO.update(employeeDTO);

                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee updated successfully").show();
                    setAll();
                    clear();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Employee not updated successfully").show();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

        }else {
            new Alert(Alert.AlertType.ERROR, "Input Valid Data").show();
        }
    }


    public void handleDeleteEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String eId = eIdtxt.getText();

        boolean isDelete = employeeBO.delete(eId);
        if (isDelete) {
            new Alert(Alert.AlertType.INFORMATION, "Employee deleted successfully").show();
            setAll();//set table
            clear();
        }else {
            new Alert(Alert.AlertType.ERROR, "Employee not deleted successfully").show();
        }
    }

    public void handleAddEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String eId = eIdtxt.getText();
        String employeeName = nameField.getText();
        String employeeRole = roleField.getText();
        String contactFieldText =contactField.getText();

        if (isValid()) {
            EmployeeDTO employeeDTO = new EmployeeDTO(eId, employeeName, employeeRole, contactFieldText);
            boolean isSAved = employeeBO.save(employeeDTO);

            if (isSAved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee added successfully").show();
                setAll();
                getCurrentEId();
                clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Employee not added successfully").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Input Valid Data").show();
        }
    }

    public void contactOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String contact = contactField.getText();
        EmployeeDTO emp = employeeBO.search(contact);

        eIdtxt.setText(String.valueOf(emp.getEmployeeId()));
        nameField.setText(emp.getName());
        roleField.setText(emp.getRole());
        contactField.setText(emp.getContact());
    }

    public void tableSearch(MouseEvent mouseEvent) {
        TableView <EmployeeTm> tableView= (TableView<EmployeeTm>) mouseEvent.getSource();
        EmployeeTm selectedEmployee = tableView.getSelectionModel().getSelectedItem();
        nameField.setText(selectedEmployee.getName());
        roleField.setText(selectedEmployee.getRole());
        contactField.setText(selectedEmployee.getContact());
        eIdtxt.setText(String.valueOf(selectedEmployee.getEmployeeId()));
    }

    public void handleAddService(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminView/AddService.fxml"));
            Parent rootNode = loader.load();
            employeeContent.getChildren().clear();
            employeeContent.getChildren().add(rootNode);

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Page Loading Error", "Failed to load the page: " + "\nError: " + e.getMessage());
        }
    }
    public void clear(){
        nameField.clear();
        roleField.clear();
        contactField.clear();
    }

    public void eNameOnKeyRelease(KeyEvent keyEvent) {
        if(nameField.getText().matches("[a-zA-Z ]+")){
            nameField.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-height: 5px;");
        }else {
            nameField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-height: 5px;");
        }
    }

    public void roleOnKeyRelease(KeyEvent keyEvent) {
        if(roleField.getText().matches("[a-zA-Z ]+")){
            roleField.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-height: 5px;");
        }else{
            roleField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-height: 5px;");
        }
    }

    public void contactOnKeyRelease(KeyEvent keyEvent) {
        if(contactField.getText().matches("\\d{10}")){
            contactField.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-height: 5px;");
        }else{
            contactField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-height: 5px;");
        }
    }
    public boolean isValid(){
        if(nameField.getText().matches("[a-zA-Z ]+")&&
            roleField.getText().matches("[a-zA-Z ]+")&&
                contactField.getText().matches("\\d{10}$")

        ){
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
