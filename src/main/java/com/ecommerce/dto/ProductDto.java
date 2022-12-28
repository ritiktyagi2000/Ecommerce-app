package com.ecommerce.dto;

import javax.validation.constraints.NotNull;

public class ProductDto {
    private @NotNull String name;
    private @NotNull String imageUrl;
    private @NotNull String description;
    private @NotNull double price;
    private @NotNull Integer categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public ProductDto(String name, String imageUrl, String description, double price, Integer categoryId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

}
