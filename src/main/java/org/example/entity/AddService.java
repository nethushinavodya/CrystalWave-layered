package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AddService {
    private String serviceId;
    private String serviceName;
    private String serviceDescription;
    private String servicePrice;
}
