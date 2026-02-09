package com.ecom.Project.service;

import com.ecom.Project.model.category;
import com.ecom.Project.payload.CategoryDTO;
import com.ecom.Project.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber , Integer pageSize ,String sortBy , String sortOrder); //getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryID);

    CategoryDTO updatecatergory(CategoryDTO categoryDTO, Long categoryId);
}
