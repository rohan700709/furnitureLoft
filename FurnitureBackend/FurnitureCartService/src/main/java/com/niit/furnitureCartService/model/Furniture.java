package com.niit.furnitureCartService.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Furniture {

    private int furnitureId;
    private String furnitureName;
    private int furniturePrice;
    private int furnitureRating;
    private String furnitureDescription;
}

