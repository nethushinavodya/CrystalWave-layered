package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Inventory {

    private String itemId;
    private String itemName;
    private String itemQuantity;
    private String itemPrice;

}