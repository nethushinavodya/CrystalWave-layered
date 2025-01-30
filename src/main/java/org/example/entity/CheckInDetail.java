package org.example.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class CheckInDetail {
    String reservationId;
    String guestId;
    String roomId;
    String checkInDate;
    String checkOutDate;
    String amount;
}
