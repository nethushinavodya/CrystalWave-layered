package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomType {
    private String roomTypeId;
    private String roomTypeName;
    private String roomTypeDescription;
    private double roomTypePrice;
}
