package com.niit.furnitureCartService.service;
import com.niit.furnitureCartService.exception.FurnitureNotFoundException;
import com.niit.furnitureCartService.exception.FurnitureAlreadyExists;
import com.niit.furnitureCartService.exception.UserNotFoundException;
import com.niit.furnitureCartService.model.Furniture;
import com.niit.furnitureCartService.model.User;

import java.util.List;

public interface CartService {

    User addFurnitureToCart(Furniture furniture, String userEmail) throws UserNotFoundException , FurnitureAlreadyExists;
    List<Furniture> getAllCartFurniture(String cartName, String userEmail) throws FurnitureNotFoundException;
    User deleteCartFurniture(int furnitureId, String userEmail) throws UserNotFoundException, FurnitureNotFoundException;

}
