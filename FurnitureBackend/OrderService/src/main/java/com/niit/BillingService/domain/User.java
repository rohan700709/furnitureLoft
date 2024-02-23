package com.niit.BillingService.domain;
import lombok.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String userName;
    private String mobileNumber;
    private List<Furniture> furnitureList;
}
