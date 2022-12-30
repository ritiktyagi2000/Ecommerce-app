package com.ecommerce.dto.cart;

import javax.validation.constraints.NotNull;

public class AddToCartDto {

    private Integer id;
    private @NotNull Long productId;
    private @NotNull Integer quantity;

    public AddToCartDto(Integer id, Long productId, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public AddToCartDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
