package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DiscountDTO {
        private String discountId;
        private String discountType;
        private String discountStartDate;
        private String discountEndDate;
        private int discountCondition;
}
