package com.ecom.Project.service;


import com.ecom.Project.exceptions.ResourceNotFoundException;
import com.ecom.Project.model.Product;
import com.ecom.Project.model.category;
import com.ecom.Project.payload.ProductDTO;
import com.ecom.Project.payload.ProductResponse;
import com.ecom.Project.repositories.ProductRepository;
import com.ecom.Project.repositories.categoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService{


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private categoryRepository categoryRepository;

    @Override
    public ProductResponse getAllProducts() {
        List<Product> products= productRepository.findAll();
        List<ProductDTO> productsDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productsDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId) {
        category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category","categoryId",categoryId));
        List<Product> products= productRepository.findByCategoryOrderByPriceAsc(category);
        List<ProductDTO> productsDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productsDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword) {
        List<Product> products= productRepository.findByProductNameLikeIgnoreCase('%'+keyword+'%');
        List<ProductDTO> productsDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productsDTOS);
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId,ProductDTO productDTO) {
        Product existingproduct = productRepository.findById(productId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Product","ProductId",productId));
        Product product = modelMapper.map(productDTO, Product.class);
        existingproduct.setProductName(product.getProductName());
        existingproduct.setDescription(product.getDescription());
        existingproduct.setQuantity(product.getQuantity());
        existingproduct.setDiscount(product.getDiscount());
        existingproduct.setPrice(product.getPrice());
        existingproduct.setSpecialPrice(product.getSpecialPrice());

        Product savedProduct = productRepository.save(existingproduct);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product","ProductId",productId));
        productRepository.delete(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO addProduct(Long categoryId, ProductDTO productDTO) {
        category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category","categoryId",categoryId));

        Product product = modelMapper.map(productDTO,Product.class);
        product.setImage("Default.png");
        product.setCategory(category);
        double specialPrice = product.getPrice() -
                ((product.getDiscount() * 0.01) * product.getPrice());
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }
}
