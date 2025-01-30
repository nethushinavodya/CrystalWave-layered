package org.example.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddToCartTM {
    String roomId;
    String guestId;
    String noOfGuests;
    String CheckInDate;
    String CheckOutDate;
    double roomPrice;
    Button button;
}
