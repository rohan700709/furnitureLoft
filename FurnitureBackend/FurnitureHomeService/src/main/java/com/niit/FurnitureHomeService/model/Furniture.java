package com.niit.FurnitureHomeService.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Furniture {

    @Id
    private int furnitureId;
    private String furnitureName;
    private int furniturePrice;
    private double furnitureRating;
    private String furnitureDescription;



}
