//package com.telusko.controller;
//
//import com.telusko.model.Product;
//import com.telusko.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/api")
//public class
//ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> getAllProducts() {
//        List<Product> productList = productService.getAllProducts();
//        return new ResponseEntity<>(productList, HttpStatus.OK);
//    }
//
//    @GetMapping("/product/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
//        if (productService.getProductById(id) != null) {
//            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//}

package com.telusko.controller;

import com.telusko.model.Image;
import com.telusko.model.Product;
import com.telusko.service.ProductService;
import com.telusko.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PostMapping("/product/{productId}/image")
    public ResponseEntity<?> uploadImage(@PathVariable Integer productId, @RequestParam("image") MultipartFile imageFile) {
        try {
            productService.addImageToProduct(productId, imageFile);
            return new ResponseEntity<>("Image uploaded successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable Integer productId) {
        Optional<Image> image = productService.getImageByProductId(productId);


        if (image.isPresent()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.valueOf(image.get().getImageType()));
//            return ResponseEntity.ok()
//                    .header("Content-Type", image.get().getImageType())
//                    .body(image.get().getImageData());
            return new ResponseEntity<>(ImageUtils.decompressImage(image.get().getImageData()), httpHeaders, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
