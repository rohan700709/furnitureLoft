package com.niit.BillingService.domain;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.TimeZone;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    @Id
    private String emailId;
//    private Date orderDate;
//    private TimeZone orderTime;
    private User user;
}
