package com.telusko.mapper;

import com.telusko.model.Product;
import com.telusko.model.ProductDTO;

public class ProductMapper {

    // Convert a Product entity to a ProductDTO
    public static ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getPrice()
        );
    }

    // Convert a ProductDTO to a Product entity
    public static Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setBrand(productDTO.getBrand());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}
