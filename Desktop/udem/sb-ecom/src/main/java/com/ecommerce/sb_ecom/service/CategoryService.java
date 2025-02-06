package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);// because this is get
    public CategoryDTO createCategories(CategoryDTO categoryDTO); // because this is post

    public CategoryDTO  deleteCategory(Long categoryId);

   public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
    //2 option for PUT method
   // public String updateCategory(Category category, Long categoryId);
}
