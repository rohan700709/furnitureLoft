package com.niit.furnitureCartService.controller;
import com.niit.furnitureCartService.exception.FurnitureAlreadyExists;
import com.niit.furnitureCartService.exception.UserNotFoundException;
import com.niit.furnitureCartService.model.Furniture;
import com.niit.furnitureCartService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v4/")
@CrossOrigin(origins="*")
@RestController
public class CartController {

    private CartService cartService;
    private ResponseEntity responseEntity;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("cartService/{userEmail}")
    public ResponseEntity<?> addToCart(@RequestBody Furniture furniture, @PathVariable("userEmail") String userEmail) throws UserNotFoundException, FurnitureAlreadyExists {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity=new ResponseEntity<>(cartService.addFurnitureToCart(furniture,userEmail), HttpStatus.CREATED);
        }
        catch (UserNotFoundException exception)
        {
            throw new UserNotFoundException();
        }
        catch (FurnitureAlreadyExists exception)
        {
            throw new FurnitureAlreadyExists();
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<>("Internal Server Error!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("cartService/cart/{userEmail}/{cartName}")
    public ResponseEntity<?> getCart(@PathVariable String cartName, @PathVariable String userEmail)
    {
        try{
            responseEntity=new ResponseEntity(cartService.getAllCartFurniture(cartName, userEmail),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            responseEntity=new ResponseEntity<>("Internal Server Error!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("cartService/cart/{userEmail}/{furnitureId}")
    public ResponseEntity<?> deleteCart(@PathVariable int furnitureId, @PathVariable String userEmail) throws UserNotFoundException {
        try{
            responseEntity=new ResponseEntity<>(cartService.deleteCartFurniture(furnitureId,userEmail),HttpStatus.OK);
        }
        catch (UserNotFoundException ue)
        {
            throw new UserNotFoundException();
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<>("Internal Server Error!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
