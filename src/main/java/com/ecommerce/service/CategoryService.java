package com.ecommerce.service;

import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
