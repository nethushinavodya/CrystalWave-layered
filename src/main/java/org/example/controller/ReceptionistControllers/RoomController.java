package org.example.controller.ReceptionistControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.bo.BOFactory;
import org.example.bo.custom.RoomBO;
import org.example.dto.AddRoomDTO;
import org.example.dto.tm.AddRoomTM;

import java.sql.SQLException;
import java.util.List;

public class RoomController {
    public TableView <AddRoomTM>roomTable;
    public TableColumn <?,?>roomIdColumn;
    public TableColumn  <?,?>roomStatusColumn;
    public TableColumn <?,?> roomNumber;
    public TableColumn  <?,?>roomTypeId;

    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOType.ROOM);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCell();
        setAll();
    }

    private void setCell() {
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomStatusColumn.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));
        roomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
    }
    private void setAll() throws SQLException, ClassNotFoundException {
        ObservableList<AddRoomTM> addRoom = FXCollections.observableArrayList();

        List<AddRoomDTO> list = roomBO.getAll();

        for (AddRoomDTO addRoomDTO : list) {
            AddRoomTM addRoomTM = new AddRoomTM();
            addRoomTM.setRoomId(addRoomDTO.getRoomId());
            addRoomTM.setRoomNumber(addRoomDTO.getRoomNumber());
            addRoomTM.setRoomStatus(addRoomDTO.getRoomStatus());
            addRoomTM.setRoomTypeId(addRoomDTO.getRoomTypeId());
            addRoom.add(addRoomTM);

        }
        roomTable.setItems(addRoom);
    }
}
