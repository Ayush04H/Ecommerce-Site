package com.ecom.Project.repositories;

import com.ecom.Project.model.category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepository extends JpaRepository<category,Long> {

    category findBycategoryname(@NotBlank @Size(min = 5,message = "Category Name Must Contain Atleast 5 Characters ") String categoryname);
}
