package com.supermarket.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Checkout Request DTO
 */
public class CheckoutRequest {
    
    @NotEmpty(message = "Cart items cannot be empty")
    @Valid
    private List<CartItemDTO> cartItems;
    
    // Constructors
    public CheckoutRequest() {
    }
    
    public CheckoutRequest(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
    
    // Getters and Setters
    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }
    
    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
