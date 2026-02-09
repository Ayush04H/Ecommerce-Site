package com.ecom.Project.repositories;

import com.ecom.Project.model.Product;
import com.ecom.Project.model.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByCategoryOrderByPriceAsc(category category);

    List<Product> findByProductNameLikeIgnoreCase(String keyword);
}
