package com.ecom.Project.service;

import com.ecom.Project.model.category;

import java.util.List;

public interface CategoryService {
    List<category> getAllCategories();
    void createCategory(category category);

    String deleteCategory(Long categoryID);

    category updatecatergory(category category, Long categoryId);
}
