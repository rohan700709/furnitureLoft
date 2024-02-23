package com.niit.furnitureCartService.service;

import com.niit.furnitureCartService.exception.FurnitureNotFoundException;
import com.niit.furnitureCartService.exception.FurnitureAlreadyExists;
import com.niit.furnitureCartService.exception.UserNotFoundException;
import com.niit.furnitureCartService.model.Cart;
import com.niit.furnitureCartService.model.Furniture;
import com.niit.furnitureCartService.model.User;
import com.niit.furnitureCartService.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    private CartRepository cartRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository){
        this.cartRepository=cartRepository;
    }

    @Override
    public User addFurnitureToCart(Furniture furniture, String userEmail) throws UserNotFoundException, FurnitureAlreadyExists {
        if(cartRepository.findById(userEmail).isEmpty())
        {
            User user = new User();
            user.setUserEmail(userEmail);
            cartRepository.save(user);
        }
        User user = cartRepository.findById(userEmail).get();

        if(user.getCart()==null){
            Cart cart = new Cart();
            cart.setCartId((int)Math.random()*1000);
            cart.setCartName("MyCart");
            List<Furniture> furnitureList = new ArrayList<>();
            furnitureList.add(furniture);
            cart.setFurniture(furnitureList);
            user.setCart(cart);
            cartRepository.save(user);
            return user;
        }

        else{
            Cart cart = user.getCart();
            List<Furniture> allFurniture = cart.getFurniture();
            for(Furniture furnituree : allFurniture){
                if(furnituree.getFurnitureId()== furniture.getFurnitureId()){
                    throw new FurnitureAlreadyExists();
                }
            }
            allFurniture.add(furniture);
            cart.setFurniture(allFurniture);
            user.setCart(cart);
            cartRepository.save(user);
            return user;
        }

    }

    @Override
    public List<Furniture> getAllCartFurniture(String cartName, String userEmail) throws FurnitureNotFoundException {
        if(cartRepository.findById(userEmail).isEmpty())
        {
            throw new FurnitureNotFoundException();
        }
        User user = cartRepository.findById(userEmail).get();
        Cart cart = user.getCart();
        return cart.getFurniture();
    }

    @Override
    public User deleteCartFurniture(int furnitureId, String userEmail) throws UserNotFoundException, FurnitureNotFoundException {
        User user1 = new User();
        if(cartRepository.findById(userEmail).isEmpty())
        {
            User user = new User();
            user.setUserEmail(userEmail);
            cartRepository.save(user);
        }
        User user=cartRepository.findById(userEmail).get();
        Cart cart = user.getCart();
        List<Furniture> furnitureList = user.getCart().getFurniture();
        for(Furniture furniture2 : furnitureList){
            if(furniture2.getFurnitureId()== furnitureId){
                furnitureList.remove(furniture2);
                cart.setFurniture(furnitureList);
                user.setCart(cart);
                user1 = cartRepository.save(user);
            }
        }
        return user1;
    }


}


