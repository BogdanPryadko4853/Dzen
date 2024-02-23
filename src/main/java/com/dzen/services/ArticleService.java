package com.dzen.services;

import com.dzen.Repositories.ArticleRepository;
import com.dzen.models.CurrentEvents;
import com.dzen.models.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<CurrentEvents> eventsList(String title) {
        if (title != null) return articleRepository.findByTitle(title);
        return articleRepository.findAll();
    }

    public CurrentEvents getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    public void saveArticle(CurrentEvents currentEvents, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            currentEvents.addImageToArticle(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            currentEvents.addImageToArticle(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            currentEvents.addImageToArticle(image3);
        }
        if (currentEvents != null) {
            log.info("Saving new Product. {}", currentEvents.getTitle());
            CurrentEvents currentEvents1 = articleRepository.save(currentEvents);
            currentEvents1.setPreviewImageId(currentEvents1.getImages().get(0).getId());
            articleRepository.save(currentEvents1);
        }
    }

    private Image toImageEntity(MultipartFile file1) throws IOException {
        Image image = new Image();
        image.setName(file1.getName());
        image.setOriginalFileName(file1.getOriginalFilename());
        image.setContentType(file1.getContentType());
        image.setSize(file1.getSize());
        image.setBytes(file1.getBytes());
        return image;
    }

    public List<CurrentEvents> getAllArticles() {
        return articleRepository.findAll();
    }


}



