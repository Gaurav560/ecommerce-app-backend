//package com.telusko.service;
//
//import com.telusko.model.Product;
//
//import java.util.List;
//
//public interface ProductService {
//    List<Product> getAllProducts();
//    Product getProductById(Integer id);
//}
package com.telusko.service;

import com.telusko.model.Product;
import com.telusko.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    Product createProduct(Product product);
    void addImageToProduct(Integer productId, MultipartFile imageFile) throws Exception;
    Optional<Image> getImageByProductId(Integer productId);
}
