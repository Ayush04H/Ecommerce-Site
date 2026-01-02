package com.ecom.Project.repositories;

import com.ecom.Project.model.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface categoryRepository extends JpaRepository<category,Long> {

}
