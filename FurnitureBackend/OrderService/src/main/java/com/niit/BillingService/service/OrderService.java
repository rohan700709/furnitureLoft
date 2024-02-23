package com.niit.BillingService.service;
import com.niit.BillingService.domain.Order;
import com.niit.BillingService.domain.Furniture;
import com.niit.BillingService.domain.User;
import com.niit.BillingService.exception.FurnitureAlreadyExists;
import com.niit.BillingService.exception.FurnitureNotFoundException;
import com.niit.BillingService.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;


public interface OrderService {

    User saveOrder(Furniture furniture, String emailId,String name,String mobile,int quantityFurniture) throws FurnitureAlreadyExists;
    List<Furniture> getOrderedFurniture(String emailId) throws UserNotFoundException;
    Furniture deleteOrder(Furniture furniture,String emailId) throws UserNotFoundException;
    byte[] getReceiptPdf(String emailId,String furnitureName) throws FurnitureNotFoundException,Exception;

}
