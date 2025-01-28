package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Discount {
        private String discountId;
        private String discountType;
        private int discountCondition;
        private String discountStartDate;
        private String discountEndDate;
}
