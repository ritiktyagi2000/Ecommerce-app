package com.ecommerce.dto.cart;

import java.util.List;

public class CartDto {
    List<CartItemDto>cartItemDtoList;
    private double totalCost;

    public CartDto() {
    }

    public CartDto(List<CartItemDto> cartItemDtoList, double totalCost) {
        this.cartItemDtoList = cartItemDtoList;
        this.totalCost = totalCost;
    }

    public List<CartItemDto> getCartItemDtoList() {
        return cartItemDtoList;
    }

    public void setCartItemDtoList(List<CartItemDto> cartItemDtoList) {
        this.cartItemDtoList = cartItemDtoList;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
