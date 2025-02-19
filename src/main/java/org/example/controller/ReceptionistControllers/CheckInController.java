package org.example.controller.ReceptionistControllers;

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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.*;
import org.example.dao.DAOFactory;
import org.example.dao.custom.PaymentDAO;
import org.example.dto.AddGuestDTO;
import org.example.dto.ReservationDTO;
import org.example.dto.RoomReserveDTO;
import org.example.dto.tm.AddGuestTM;
import org.example.dto.tm.AddToCartTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckInController {

    public TextField nameField;
    public TextField phoneField;
    public TextField emailField;
    public TextField addressField;
    public ComboBox <String>guestIdCmb;
    public ComboBox <String>paymentMethodComboBox;
    public ComboBox paymentStatusComboBox;
    public AnchorPane checkInContent;
    public TextField paymentAmountField;
    public Button reservebtn;
    public TextField totalPrice;
    public TextField roomType;
    public DatePicker checkOutDatePicker;
    public DatePicker checkInDatePicker;
    public ComboBox roomCapacityComboBox;
    public TextField reserveId;
    public TableColumn <?,?> roomid;
    public TableColumn <?,?>gid;
    public TableColumn <?,?>checkIn;
    public TableColumn <?,?>roomPrice;
    public TableColumn <?,?>remove;
    public TableColumn <?,?>checkOut;
    public TableView<AddToCartTM> tableView;
    public TextField noOfGuest;
    public TableColumn <?,?> noOfGuests;
    public ComboBox<String> roomIdCmb;
    public ComboBox <String> disIdCmb;
    public TextField conditionField;
    public TextField roomTypePrice;

    ObservableList<AddToCartTM> cartTMS = FXCollections.observableArrayList();
    String PaymentId;
    double Total;
    String disId;
    double total;

    DiscountBO discountBO = (DiscountBO) BOFactory.getInstance().getBO(BOFactory.BOType.DISCOUNT);
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOM);
    GuestBO guestBO = (GuestBO) BOFactory.getInstance().getBO(BOFactory.BOType.GUEST);
    ReservtionBO reservationBO = (ReservtionBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);
    RoomTypeBO roomTypeBO = (RoomTypeBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOMTYPE);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    public void initialize() throws SQLException, ClassNotFoundException {
        setcmb();
        getCurrentid();
        getPaymentid();
        setCellValue();
        setAll();
        setRoomIdCmb();
        setdisIdCmb();
        nameField.setDisable(true);
        phoneField.setDisable(true);
        emailField.setDisable(true);
        addressField.setDisable(true);
        conditionField.setDisable(true);
        roomType.setDisable(true);
    }

    private void setdisIdCmb() throws SQLException, ClassNotFoundException {
        ObservableList<String> discount = FXCollections.observableArrayList();
        List<String> discountId = discountBO.getDiscount();

        discount.addAll(discountId);
        disIdCmb.setItems(discount);
    }

    private void setRoomIdCmb() throws SQLException, ClassNotFoundException {
        ObservableList<String> room = FXCollections.observableArrayList();
        List<String> roomId = roomBO.getActiveRoom();

        room.addAll(roomId);

        roomIdCmb.setItems(room);
    }

    private void setAll() {

    }

    private void setCellValue() {
        roomid.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        gid.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        noOfGuests.setCellValueFactory(new PropertyValueFactory<>("noOfGuests"));
        checkIn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        checkOut.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        roomPrice.setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
        remove.setCellValueFactory(new PropertyValueFactory<>("button"));

        tableView.setItems(cartTMS);
    }


    private void setcmb() throws SQLException, ClassNotFoundException {
        ObservableList<String> guests = FXCollections.observableArrayList();
        List<AddGuestTM> idList = guestBO.getAll();
        for (AddGuestTM addGuestTM : idList) {
            String guestId = addGuestTM.getGuestId();

            guests.add(guestId);
        }
        guestIdCmb.setItems(guests);
    }

    public void handleNewGuest(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReceptionistView/AddNewGuest.fxml"));
            Parent rootNode = loader.load();
            checkInContent.getChildren().clear();
            checkInContent.getChildren().add(rootNode);

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Page Loading Error", "Failed to load the page: " + "\nError: " + e.getMessage());

        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void clear(){
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
        guestIdCmb.setValue(null);
        paymentMethodComboBox.setValue(null);
        paymentStatusComboBox.setValue(null);
        paymentAmountField.clear();
        roomType.clear();
        roomIdCmb.setValue(null);
        noOfGuest.clear();
        roomCapacityComboBox.setValue(null);
        checkOutDatePicker.setValue(null);
        checkInDatePicker.setValue(null);
        disIdCmb.setValue(null);
        conditionField.clear();

    }
    public void handleCancel(ActionEvent actionEvent) {
        clear();
    }

    public void handleAddToCart(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String roomId = roomIdCmb.getValue();
        String guestId = guestIdCmb.getValue();
        String noOfGuests = noOfGuest.getText();
        String checkInDate = checkInDatePicker.getValue().toString();
        String checkOutDate = checkOutDatePicker.getValue().toString();
        Double roomPrice = Double.valueOf(roomTypePrice.getText());


        if(checkInDate != null && !checkInDate.isEmpty()){
            if (isValid() ) {
                if (disIdCmb.getValue() != null) {
                    System.out.println(disIdCmb.getValue());
                    disId = disIdCmb.getValue();
                    int discout = discountBO.search(Integer.parseInt(disIdCmb.getValue()));
                    System.out.println("DIS  " + discout);
                    double price = roomPrice;
                    System.out.println("PRICE" + price);
                    double v = price * discout / 100;
                    System.out.println("PRICE" + v);
                    double newprice = price - v;
                    System.out.println("NEWPRICE" + newprice);

                    roomPrice = newprice;
                    System.out.println("NEWPRICE" + roomPrice);
                }
                for (AddToCartTM cartTM : cartTMS) {
                    if (cartTM.getRoomId().equals(roomId)) {
                        new Alert(Alert.AlertType.INFORMATION, "Room already exists").show();
                        return;
                    }
                }

                Button btn = new Button("Remove");

                AddToCartTM addToCartTM = new AddToCartTM(roomId, guestId, noOfGuests, checkInDate, checkOutDate, roomPrice, btn);

                btn.setOnAction(event -> {

                    cartTMS.remove(addToCartTM);

                    tableView.refresh();
                    setTotal();
                });
                cartTMS.add(addToCartTM);
                setTotal();
            }else {
                new Alert(Alert.AlertType.INFORMATION, "Input Valid Data").show();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Please enter a valid date").show();
        }
}

    public void guestCmb(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String guestId = guestIdCmb.getSelectionModel().getSelectedItem();

        AddGuestDTO guestDTO = guestBO.search(guestId);

        nameField.setText(guestDTO.getGuestName());
        addressField.setText(guestDTO.getGuestAddress());
        emailField.setText(guestDTO.getGuestEmail());
        phoneField.setText(guestDTO.getGuestPhone());
    }

    public void handleReserve(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        double payment= Double.parseDouble(paymentAmountField.getText());
        System.out.println(payment);
        System.out.println(total);
        if (payment>=total){
            Total = 0;

            System.out.println("clicked");
            String reservationId = reserveId.getText();
            String roomId = roomIdCmb.getValue();
            String guestId = guestIdCmb.getSelectionModel().getSelectedItem();
            String noOfGuests = noOfGuest.getText();
            String paymentmethod = (String) paymentMethodComboBox.getValue();
            String checkInDate = checkInDatePicker.getValue().toString();
            String checkOutDate = checkOutDatePicker.getValue().toString();
            String roomPrice = paymentAmountField.getText();

            ArrayList<RoomReserveDTO> reservationDTOS = new ArrayList<>();

            for (AddToCartTM addToCartTM : cartTMS) {
                double price = addToCartTM.getRoomPrice();
                Total+=price;

                System.out.println("QWQW");
                System.out.println(addToCartTM);
                RoomReserveDTO roomReserveDTO = new RoomReserveDTO(
                        reservationId,
                        addToCartTM.getRoomId(),
                        addToCartTM.getRoomPrice()

                );
                reservationDTOS.add(roomReserveDTO);
            }

            ReservationDTO reservationDTO = new ReservationDTO(reservationId,guestId,roomId,checkInDate,checkOutDate,reservationDTOS);
            System.out.println(reservationDTO);
            System.out.println("discount id  " + disId);

            boolean isSave = reservationBO.saveReservation(reservationDTO,Total,PaymentId,paymentmethod,disId);


            if (isSave) {
                new Alert(Alert.AlertType.CONFIRMATION, "Reservation has been saved successfully").showAndWait();
                getCurrentid();
                getPaymentid();
                Total = 0;
                cartTMS.clear();
                tableView.refresh();
                totalPrice.setText(null);
            }else {
                new Alert(Alert.AlertType.ERROR, "Reservation has not been saved successfully").showAndWait();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Input Valid Payment Amount").show();
        }
    }
    private void getCurrentid() throws ClassNotFoundException {
        try {
            String currentId = reservationBO.getCurrentId();

            String nextLocationIdId = generateNextReservationId(currentId);
            reserveId.setText(nextLocationIdId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getPaymentid() throws ClassNotFoundException {
        try {
            String currentId = paymentDAO.getCurrentId();

            PaymentId = generateNextReservationId(currentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTotal(){
        total =0.0;
        for(AddToCartTM addToCartTM : cartTMS){
            total += addToCartTM.getRoomPrice();
        }
        totalPrice.setText(String.valueOf(total));
    }

    private String generateNextReservationId(String currentId) {
        try {
            if (currentId != null) {
                int id = Integer.parseInt(currentId);
                id++;

                return String.valueOf(id);
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        return "1";
    }

    public void tableView(MouseEvent mouseEvent) {
    }

    public void roomId(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String roomId = roomIdCmb.getValue();

        String Type  = reservationBO.search(roomId);
        String roomPrice = roomTypeBO.getPrice(Type);
        roomTypePrice.setText(roomPrice);
        roomType.setText(Type);
    }

    public void discountId(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String discountId = disIdCmb.getValue();

        int discount = Integer.parseInt(discountId);
        int disCon = discountBO.search(discount);
        conditionField.setText(disCon+"%");
    }

    public void amountOnKeyRelease(KeyEvent keyEvent) {
        if (paymentAmountField.getText().matches("\\d{4,}")){
            paymentAmountField.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-height: 5px;");
        }else{
            paymentAmountField.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-height: 5px;");
        }
    }

    public void guestNoOnKeyRelease(KeyEvent keyEvent) {
        if(noOfGuest.getText().matches("\\d{1,}")){
            noOfGuest.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-height: 5px;");
        }else {
            noOfGuest.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-height: 5px;");
        }
    }
    public boolean isValid() {
        if(noOfGuest.getText().matches("\\d{1,}")){
            return true;
        }else {
            return false;
        }
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
