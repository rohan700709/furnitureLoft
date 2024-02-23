package com.niit.FurnitureSortingSearching.comparator;

import com.niit.FurnitureSortingSearching.model.FurniturePOJO;

import java.util.Comparator;

public class HighToLowRating implements Comparator<FurniturePOJO> {
    @Override
    public int compare(FurniturePOJO o1, FurniturePOJO o2) {
        if((o1.getFurnitureRating()>(o2.getFurnitureRating())))
            return -1;
        if((o1.getFurnitureRating()>(o2.getFurnitureRating())))
            return 1;
        else
            return 0;
    }
}
