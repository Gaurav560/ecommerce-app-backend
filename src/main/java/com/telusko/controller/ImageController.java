package com.telusko.controller;

import com.telusko.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private ImageService imageService;

    //controller method to upload images
    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

        String uploadImage = imageService.uploadImage(file);
        if (uploadImage != null) {
            return new ResponseEntity<>(uploadImage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //controller method to download images
    @GetMapping("/image/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Integer id) {
        byte[] imageData = imageService.downloadImage(id);

        //defining http headers so that client or browser can understand what type of content is coming and
        // retrieve it accordingly otherwise it will give a random binary data or some unusual data
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_PNG);

        if (imageData != null) {
            //the sequence is: body, header, status
            return new ResponseEntity<>(imageData, httpHeaders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
