package com.telusko.service;


import com.telusko.model.Product;
import com.telusko.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();

    }

    @Override
    public Product getProductById(Integer id) {
        Optional<Product> optionalProduct=productRepo.findById(id);
        if (optionalProduct.isPresent()){
            return optionalProduct.get();
        }else {
            return null;
        }
    }

}
