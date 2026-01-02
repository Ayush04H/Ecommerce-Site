package com.ecom.Project.service;

import com.ecom.Project.model.category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private List<category> categories = new ArrayList<>();
    private long nextID = 1L;

    @Override
    public List<category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(category category) {
        category.setCategoryId(nextID++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryID) {
        category category = categories.stream()
                .filter(c ->c.getCategoryId() == categoryID)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));

        categories.remove(category);

        return "Category with CategoryID: " +categoryID +" Delted Succesfully";
    }

    @Override
    public category updatecatergory(category category, Long categoryId) {
        Optional<category> optionalCategory = categories.stream()
                .filter(c ->c.getCategoryId() == categoryId)
                .findFirst();

        if (optionalCategory.isPresent()){
            category existingCategory = optionalCategory.get();
            existingCategory.setCategoryname(category.getCategoryname());
            return existingCategory;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found");
        }

        //return null;
    }

}
