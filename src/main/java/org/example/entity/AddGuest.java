package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AddGuest {

    private String guestId;
    private String guestName;
    private String guestPhone;
    private String guestAddress;
    private String guestEmail;

}
