package com.ecommerce.controller;

import com.ecommerce.common.ApiResponse;
import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class CategoryController {
    @Autowired
 CategoryService categoryService;


    @PostMapping("/create")
    public String createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return "category-created-successfully";
    }
    @GetMapping("/list")
    public List<Category> getListOfCategory(){

        return categoryService.getAllCategories();
    }

    @PostMapping("/update/{categoryName}")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody Category updatedCategory , @PathVariable String categoryName ){

        if(!categoryService.findByCategoryName(categoryName)){
            return new ResponseEntity<>(new ApiResponse(false,"Category Not found"), HttpStatus.NOT_FOUND);
        }else {
            Category category = categoryService.updateCategory(categoryName, updatedCategory);
            return new ResponseEntity<>(new ApiResponse(true, "successfully found"), HttpStatus.FOUND);
        }
    }

}
