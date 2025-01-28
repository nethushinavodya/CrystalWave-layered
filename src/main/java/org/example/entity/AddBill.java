package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddBill {
    private String billid;
    private String rid;
    private Double tot;
    private String date;
    
    private List<String> serviceList;
}
