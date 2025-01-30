package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CheckInDetailDTO {
    String reservationId;
    String guestId;
    String roomId;
    String checkInDate;
    String checkOutDate;
    String amount;
}
