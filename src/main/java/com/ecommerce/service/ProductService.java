package com.ecommerce.service;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public void createProduct(ProductDto productDto, Category category) throws DataIntegrityViolationException {
    Product product=new Product();



            product.setDescription(productDto.getDescription());
            product.setName(productDto.getName());
            product.setImageUrl(productDto.getImageUrl());
            product.setPrice(productDto.getPrice());
            product.setCategory(category);
            productRepository.save(product);


    }
    public ProductDto productToDto(Product p){
        ProductDto productDto = new ProductDto();
        productDto.setName(p.getName());
        productDto.setDescription(p.getDescription());
        productDto.setCategoryId(p.getCategory().getId());
        productDto.setPrice(p.getPrice());
        productDto.setImageUrl(p.getImageUrl());
       return productDto;

    }

    public List<ProductDto> getAllProducts() {


        List<Product> productList = productRepository.findAll();
        List<ProductDto>productDtoList=new ArrayList<>();
         for(Product p:productList) {

             ProductDto productDto = productToDto(p);
             productDtoList.add(productDto);
         }
         return productDtoList;
    }


    public void updateProduct(String productName, ProductDto updatedDto) {
        Product product = productRepository.findOneByName(productName);
        ProductDto productDto = productToDto(product);

        productDto.setImageUrl(updatedDto.getImageUrl());
        productDto.setCategoryId(updatedDto.getCategoryId());
        productDto.setPrice(updatedDto.getPrice());
        productDto.setDescription(updatedDto.getDescription());
        productDto.setName(updatedDto.getName());

        Product newProduct = dtoToProduct(productDto);
        product.setCategory(newProduct.getCategory());
        product.setPrice(newProduct.getPrice());
        product.setName(newProduct.getName());
        product.setImageUrl(newProduct.getImageUrl());
        product.setDescription(newProduct.getDescription());
        productRepository.save(product);
    }

    private Product dtoToProduct(ProductDto productDto) {

        Product p=new Product();
        p.setDescription(productDto.getDescription());
        p.setName(productDto.getName());
        p.setImageUrl(productDto.getImageUrl());
        p.setPrice(productDto.getPrice());
        Optional<Category> c=categoryRepository.findById(productDto.getCategoryId());
        Category category=c.get();
        p.setCategory(category);
        return p;



    }

    public Product findByProductId(Long productId) throws ProductNotFoundException {

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){

            throw new ProductNotFoundException("Product Id is invalid "+ productId);
        }
        Product product=optionalProduct.get();
        return product;
    }
}
