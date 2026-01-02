package com.ecom.Project.service;

import com.ecom.Project.model.category;
import com.ecom.Project.repositories.categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    //private List<category> categories = new ArrayList<>();
    private long nextID = 1L;

    @Autowired
    private categoryRepository CategoryRepository;

    @Override
    public List<category> getAllCategories() {
        return CategoryRepository.findAll();
    }

    @Override
    public void createCategory(category category) {
       // category.setCategoryId(nextID++);
        CategoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryID) {

        //List<category> categories = CategoryRepository.findAll();
        category category = CategoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));


        CategoryRepository.delete(category);
        return "Category with CategoryID: " +categoryID +" Delted Succesfully";
    }

    @Override
    public category updatecatergory(category category, Long categoryId) {

        Optional<category> savedcategoryOptional = CategoryRepository.findById(categoryId);

        category savedCategory = savedcategoryOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resouce Not Found"));

        category.setCategoryId(categoryId);

        savedCategory = CategoryRepository.save(category);
        return savedCategory;

        //return null;
    }

}
