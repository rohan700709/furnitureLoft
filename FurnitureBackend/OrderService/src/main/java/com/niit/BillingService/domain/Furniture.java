package com.niit.BillingService.domain;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Furniture {
    private int furnitureId;
    private String furnitureName;
    private int furniturePrice;
    private String orderDate;
    private String orderTime;
    private int furnitureQuantity;
    private String furnitureDescription;
}
