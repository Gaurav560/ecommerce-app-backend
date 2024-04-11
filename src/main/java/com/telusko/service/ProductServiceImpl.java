package com.telusko.service;

import com.telusko.mapper.ProductMapper;
import com.telusko.model.Product;
import com.telusko.model.ProductDTO;
import com.telusko.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<ProductDTO> getAllProductDTOs() {
        List<Product> productList = productRepo.findAll();
        return productList.stream()
                .map(product -> ProductMapper.toDTO(product))
                .collect(Collectors.toList());
    }

}
