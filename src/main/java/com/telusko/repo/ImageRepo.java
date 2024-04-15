package com.telusko.repo;

import com.telusko.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepo extends JpaRepository<Image, Integer> {
     Optional<Image> findById(Integer id);
}
