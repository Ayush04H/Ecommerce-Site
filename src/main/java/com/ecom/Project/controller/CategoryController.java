package com.ecom.Project.controller;


import com.ecom.Project.config.AppConstants;
import com.ecom.Project.model.category;
import com.ecom.Project.payload.CategoryDTO;
import com.ecom.Project.payload.CategoryResponse;
import com.ecom.Project.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class CategoryController {

   private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public  ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMER , required = false) Integer pageNumber,
            @RequestParam(name = "pageSize" , defaultValue = AppConstants.PAGE_SIZE ,required = false) Integer pageSize,
            @RequestParam(name = "sortBy" ,defaultValue = AppConstants.SORT_CATEGORIES_BY , required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = AppConstants.SORT_DIR , required = false) String sortOrder){

        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber,pageSize ,sortBy,sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }


    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>("Cateogery Added Succesfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryID}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryID){
        CategoryDTO deleteCategory = categoryService.deleteCategory(categoryID);
        return new ResponseEntity<>(deleteCategory, HttpStatus.OK);

    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updatecategory(@RequestBody CategoryDTO categoryDTO ,
                                                 @PathVariable Long categoryId){
        CategoryDTO savedcategoryDTO=  categoryService.updatecatergory(categoryDTO , categoryId);
            return new ResponseEntity<>(savedcategoryDTO,HttpStatus.OK);
    }


}
