package com.niit.furnitureCartService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @Id
    private String userEmail;
    private Cart cart;

    public User() {
    }

    public User(String userEmail, Cart cart) {
        this.userEmail = userEmail;
        this.cart = cart;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmail='" + userEmail + '\'' +
                ", cart=" + cart +
                '}';
    }
}
