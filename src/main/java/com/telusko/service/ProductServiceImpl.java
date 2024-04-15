
package com.telusko.service;

import com.telusko.model.Product;
import com.telusko.model.Image;
import com.telusko.repo.ProductRepo;
import com.telusko.repo.ImageRepo;
import com.telusko.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

     @Autowired
     private ProductRepo productRepo;

     @Autowired
     private ImageRepo imageRepo;

     @Override
     public List<Product> getAllProducts() {
          return productRepo.findAll();
     }

     @Override
     public Product getProductById(Integer id) {
          return productRepo.findById(id).orElse(null);
     }

     @Override
     public Product createProduct(Product product) {
          return productRepo.save(product);
     }

     @Override
     public void addImageToProduct(Integer productId, MultipartFile imageFile) throws Exception {
          Product product = productRepo.findById(productId).orElseThrow(() -> new Exception("Product not found"));
          Image image = new Image();
          image.setImageName(imageFile.getOriginalFilename());
          image.setImageType(imageFile.getContentType());
          image.setImageData(ImageUtils.compressImage(imageFile.getBytes()));
          image.setProduct(product);
          product.setImage(image);
          imageRepo.save(image);
     }


     @Override
     public Optional<Image> getImageByProductId(Integer productId) {
          return imageRepo.findByProductId(productId);
     }
}
