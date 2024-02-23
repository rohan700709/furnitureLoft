package com.niit.FurnitureSortingSearching.comparator;
import com.niit.FurnitureHomeService.model.Furniture;
import com.niit.FurnitureSortingSearching.model.FurniturePOJO;

import java.util.Comparator;



public class HighToLowComparator implements Comparator<FurniturePOJO> {

    @Override
    public int compare(FurniturePOJO o1, FurniturePOJO o2) {

        if (o1.getFurniturePrice() < o2.getFurniturePrice())
            return 1;
        if (o1.getFurniturePrice() > o2.getFurniturePrice())
            return -1;
        else
            return 0;
    }
}
