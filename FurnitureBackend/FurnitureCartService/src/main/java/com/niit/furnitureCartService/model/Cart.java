package com.niit.furnitureCartService.model;
import java.util.List;

public class Cart {

    private int cartId;
    private String cartName;
    private List<Furniture> furniture;

    public Cart() {
    }

    public Cart(int cartId, String cartName, List<Furniture> furniture) {
        this.cartId = cartId;
        this.cartName = cartName;
        this.furniture = furniture;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public List<Furniture> getFurniture() {
        return furniture;
    }

    public void setFurniture(List<Furniture> furniture) {
        this.furniture = furniture;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", cartName='" + cartName + '\'' +
                ", furniture=" + furniture +
                '}';
    }
}
