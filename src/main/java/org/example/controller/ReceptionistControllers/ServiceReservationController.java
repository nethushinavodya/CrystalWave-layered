package org.example.controller.ReceptionistControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.bo.BOFactory;
import org.example.bo.custom.BillBO;
import org.example.bo.custom.ReservtionBO;
import org.example.bo.custom.ServiceBO;
import org.example.dto.AddBilDto;
import org.example.dto.AddServiceDTO;
import org.example.dto.tm.ServiceCartTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceReservationController {
    public ComboBox<String> reservationIdCmb;
    public ComboBox <String>serviceIdCmb;
    public TableView <ServiceCartTM>tableView;
    public TableColumn  <?,?> remove;
    public TableColumn <?,?> reservationId;
    public TableColumn<?,?>  serviceId;
    public Label Bill_lbl;
    public TableColumn  <?,?> servicePricecol;

    Double total=0.0;

    ObservableList<ServiceCartTM> cartTMS = FXCollections.observableArrayList();
    BillBO billBO = (BillBO) BOFactory.getInstance().getBO(BOFactory.BOType.BILL);
    ServiceBO serviceBO = (ServiceBO) BOFactory.getInstance().getBO(BOFactory.BOType.SERVICE);
    ReservtionBO reservationBO = (ReservtionBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);

    public void initialize() throws SQLException, ClassNotFoundException {
        getbillId();
        setCellValue();
        setRCmb();
        setScmb();
    }


    private void getbillId() throws SQLException, ClassNotFoundException {
         try {
             String currentId = billBO.getCurrentId();
             String nextId = generateNextReservationId(currentId);
             Bill_lbl.setText(nextId);
         } catch (Exception e) {
             new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
         }

    }

    private String generateNextReservationId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("B");
            int idNum = Integer.parseInt(split[1]);
            return "B" + ++idNum;
        } else {
            return "B1";
        }
    }

    private void setCellValue() {
        reservationId.setCellValueFactory(new PropertyValueFactory<>("rid"));
        serviceId.setCellValueFactory(new PropertyValueFactory<>("sid"));
        servicePricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
        remove.setCellValueFactory(new PropertyValueFactory<>("cancelBtn"));
        tableView.setItems(cartTMS);

    }

    private void setScmb() throws SQLException, ClassNotFoundException {
        ObservableList<String> items = FXCollections.observableArrayList();

        List<AddServiceDTO>list = serviceBO.getAll();

        for (AddServiceDTO addServiceDTO : list) {
            items.add(addServiceDTO.getServiceId());

        }
        serviceIdCmb.setItems(items);
    }

    private void setRCmb() throws SQLException, ClassNotFoundException {
        ObservableList<String> rlist = reservationBO.getAll();
        reservationIdCmb.setItems(rlist);

    }

    public void addToCart(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String reservationId = reservationIdCmb.getSelectionModel().getSelectedItem();
        String serviceId = serviceIdCmb.getSelectionModel().getSelectedItem();


        for(ServiceCartTM cartTM : cartTMS) {
            if (cartTM.getSid().equals(serviceId)) {
                new Alert(Alert.AlertType.INFORMATION, "service already exists").show();
                return;
            }
        }

        Button button =new Button("remove");

        String price = serviceBO.getprice(serviceId);
        double pp = Double.parseDouble(price);

        ServiceCartTM serviceCartTM = new ServiceCartTM(reservationId, serviceId, pp, button);
        tableView.refresh();
        button.setOnAction(event -> {

            cartTMS.remove(serviceCartTM);

            tableView.refresh();
        });

        cartTMS.add(serviceCartTM);

    }

    public void tableView(MouseEvent mouseEvent) {
    }

    public void addServicesOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String billId  = Bill_lbl.getText();
        String rid = reservationIdCmb.getSelectionModel().getSelectedItem();

        List<String> sidList =new ArrayList<>();

        for (ServiceCartTM serviceCartTM : cartTMS) {
            String id = serviceCartTM.getSid();
            sidList.add(id);
            double price = serviceCartTM.getPrice();
            System.out.println(price);
            total += price;
        }
        String date = LocalDate.now().toString();

        AddBilDto addBilDto = new AddBilDto(billId, rid, total, date, sidList);

        boolean isSaved = billBO.save(addBilDto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Service added").show();
            total =0.0;
            getbillId();

        }

    }
}
