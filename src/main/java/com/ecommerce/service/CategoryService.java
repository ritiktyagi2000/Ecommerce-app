package com.ecommerce.service;

import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
CategoryRepository categoryRepository;


public void createCategory(Category category){
    categoryRepository.save(category);
}

public List<Category> getAllCategories(){
    return categoryRepository.findAll();
}

    public Category updateCategory(String categoryName, Category updatedCategory) {

    Category category=categoryRepository.findByCategoryName(categoryName);
    category.setCategoryName(updatedCategory.getCategoryName());
    category.setDescription(updatedCategory.getDescription());
    category.setImageUrl(updatedCategory.getImageUrl());
    categoryRepository.save(category);
    return category;
    }

    public boolean findByCategoryName(String categoryName) {

    if(categoryRepository.findByCategoryName(categoryName)==null){
        return false;
    }else{
        return true;
    }

    }
}
