package com.ecom.Project.service;

import com.ecom.Project.payload.ProductDTO;
import com.ecom.Project.payload.ProductResponse;

public interface ProductService {


    ProductDTO addProduct(Long categoryId, ProductDTO product);
    ProductResponse getAllProducts();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchProductByKeyword(String keyword);

    ProductDTO updateProduct(Long productId, ProductDTO product);

    ProductDTO deleteProduct(Long productId);
}
