package com.niit.FurnitureSortingSearching.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

    @Document
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class FurniturePOJO {
        @Id
        private int furnitureId;
        private String furnitureName;
        private int furniturePrice;
        private double furnitureRating;
        private String furnitureDescription;
    }

