package com.ecommerce.service;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {
    Product product=new Product();
    product.setDescription(productDto.getDescription());
    product.setName(productDto.getName());
    product.setImageUrl(productDto.getImageUrl());
    product.setPrice(productDto.getPrice());
    product.setCategory(category);
    productRepository.save(product);

    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }
}
