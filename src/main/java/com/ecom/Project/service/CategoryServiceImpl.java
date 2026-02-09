package com.ecom.Project.service;

import com.ecom.Project.exceptions.APIException;
import com.ecom.Project.exceptions.NoCatgoryException;
import com.ecom.Project.exceptions.ResourceNotFoundException;
import com.ecom.Project.model.category;
import com.ecom.Project.payload.CategoryDTO;
import com.ecom.Project.payload.CategoryResponse;
import com.ecom.Project.repositories.categoryRepository;
import org.apache.catalina.authenticator.SavedRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    //private List<category> categories = new ArrayList<>();
    //private long nextID = 1L;

    @Autowired
    private categoryRepository CategoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryResponse getAllCategories(Integer pageNumber , Integer pageSize ,String sortBy , String sortOrder) {


        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize ,sortByAndOrder);
        Page<category> categoryPage = CategoryRepository.findAll(pageDetails);

        List<category> Foundcategory = categoryPage.getContent();
        if(Foundcategory.isEmpty()){
            throw new NoCatgoryException("No category Found");
        }

        List<CategoryDTO> categoryDTOS = Foundcategory.stream()
                .map(category -> modelMapper.map(category , CategoryDTO.class))
                .toList();
        //Instead List we need type Category Response

        CategoryResponse categoryresponse = new CategoryResponse();
        categoryresponse.setContent(categoryDTOS);
        categoryresponse.setPageNumber(categoryPage.getNumber());
        categoryresponse.setPageSize(categoryPage.getSize());
        categoryresponse.setTotalElements(categoryPage.getTotalElements());
        categoryresponse.setTotalPages(categoryPage.getTotalPages());
        categoryresponse.setLastPage(categoryPage.isLast());
        return categoryresponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
       // category.setCategoryId(nextID++);
        category Category = modelMapper.map(categoryDTO , category.class);
        //Category.setCategoryId(null);
        category savedcategory = CategoryRepository.findBycategoryname(Category.getCategoryname());
        if(savedcategory != null){
            throw new APIException("Catgeory with Name : "+ Category.getCategoryname() + " Already Exists");
        }
        category SavedCategory = CategoryRepository.save(Category);

        return modelMapper.map(SavedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryID) {

        //List<category> categories = CategoryRepository.findAll();
        category category = CategoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("category","categoryID",categoryID));


        CategoryRepository.delete(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updatecatergory(CategoryDTO categoryDTO, Long categoryId) {

        Optional<category> savedcategoryOptional = CategoryRepository.findById(categoryId);
        category category = modelMapper.map(categoryDTO, category.class);

        category savedCategory = savedcategoryOptional
                .orElseThrow(() -> new ResourceNotFoundException("category","categoryID",categoryId));

        category.setCategoryId(categoryId);

        savedCategory = CategoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);

        //return null;
    }

}
