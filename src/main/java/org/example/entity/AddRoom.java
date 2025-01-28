package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AddRoom {
    private String roomId;
    private String roomNumber;
    private String roomStatus;
    private String roomTypeId;


}
