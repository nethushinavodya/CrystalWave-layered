package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Reservation {
    String reservationId;
    String guestId;
    String roomId;
    String CheckInDate;
    String CheckOutDate;

    private ArrayList<RoomReserve>roomReserves;
}
