package com.dzen.controllers;

import com.dzen.Repositories.ImageRepository;
import com.dzen.models.Image;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.ByteArrayInputStream;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageController {
    ImageRepository imageRepository;
    @GetMapping("/images/{id}")
  ResponseEntity<?> getImageById(@PathVariable Long id){
      Image image = imageRepository.findById(id).orElse(null);
      return ResponseEntity.ok()
              .header("fileName",image.getOriginalFileName())
              .contentType(MediaType.valueOf(image.getContentType()))
              .contentLength(image.getSize())
              .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
  }
}
