package com.niit.FurnitureSortingSearching.service;
import com.niit.FurnitureHomeService.model.Furniture;
import com.niit.FurnitureSortingSearching.exception.FurnitureNotFoundExceptions;
import com.niit.FurnitureSortingSearching.model.FurniturePOJO;
import java.util.ArrayList;
import java.util.List;

public interface FurnitureService {

    List<FurniturePOJO> lowToHighPrice() throws FurnitureNotFoundExceptions;
    List<FurniturePOJO> highToLowPrice() throws FurnitureNotFoundExceptions;
    List<FurniturePOJO> highToLowRating() throws FurnitureNotFoundExceptions;
    List<FurniturePOJO>  searchFurnitureItems(String itemName) throws FurnitureNotFoundExceptions;

}
