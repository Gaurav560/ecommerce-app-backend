package com.telusko.service;

import com.telusko.model.Image;
import com.telusko.repo.ImageRepo;
import com.telusko.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepo imageRepo;

    //method to upload image
    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("no image found");
        }

        Image image = Image
                .builder()
                .imageName(file.getOriginalFilename())
                .imageType(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build();


        Image savedImage = imageRepo.save(image);
        if (savedImage != null) {
            return "Image uploaded successfully" + file.getOriginalFilename();
        } else {
            return null;
        }
    }

    //method to download image
    public byte[] downloadImage(Integer id) {
        Optional<Image> optionalImage = imageRepo.findById(id);
        if (optionalImage.isPresent()) {
            return optionalImage.get().getImageData();
        } else {
            return null;
        }
    }
}
