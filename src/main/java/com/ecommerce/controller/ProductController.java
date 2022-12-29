package com.ecommerce.controller;

import com.ecommerce.common.ApiResponse;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Constants;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/add")
   public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) throws DataIntegrityViolationException {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, " Category is not found"), HttpStatus.BAD_REQUEST);
        }
        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, " Product has been added"), HttpStatus.CREATED);
    }

        @GetMapping("/list")
        public ResponseEntity<List<ProductDto>> getListOfProduct(){

            return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
        }

        @PutMapping("/update/{productName}")
        public ResponseEntity<ApiResponse>updateProduct(@PathVariable String productName,@RequestBody ProductDto productDto){
            if(productRepository.findOneByName(productName)==null){
                return new ResponseEntity<>(new ApiResponse(false,"Product is not found "),HttpStatus.BAD_REQUEST);
            }else {
             Optional<Category> c=   categoryRepository.findById(productDto.getCategoryId());
                if (!c.isPresent()) {
                    return new ResponseEntity<>(new ApiResponse(false, "Category is not found "), HttpStatus.BAD_REQUEST);
                }
                    productService.updateProduct(productName, productDto);
                    return new ResponseEntity<>(new ApiResponse(true, "updated Product successfully"), HttpStatus.OK);
            }
        }
    @DeleteMapping("/delete/{description}")
        ResponseEntity<ApiResponse>deleteProductByPrice(@PathVariable String description){

        Product product = productRepository.findByDescription(description);
        productRepository.delete(product);
        return new ResponseEntity<>(new ApiResponse(true,"Deleted successfully"),HttpStatus.OK);
        }




}
