package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AddServiceEmployeeDTO {
    private String EmployeeId;
    private String ServiceId;
}
