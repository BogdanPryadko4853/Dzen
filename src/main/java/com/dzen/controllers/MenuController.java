package com.dzen.controllers;

import com.dzen.models.CurrentEvents;
import com.dzen.services.ArticleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuController {

    final ArticleService articleService;
    private CurrentEvents currentEvents;

    @GetMapping("/")
    public String product(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("products", articleService.eventsList(title));
        return "mainview";
    }

    @GetMapping("/article/watch")
    public String showAllArticles(Model model) {
        List<CurrentEvents> allArticles = articleService.getAllArticles();
        model.addAttribute("articles", allArticles);
        return "articleview";
    }


    @PostMapping("/article/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                @ModelAttribute CurrentEvents currentEvents) throws IOException {
        articleService.saveArticle(currentEvents, file1, file2, file3);
        return "redirect:/";
    }


    @PostMapping("article/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "redirect:/";
    }

    @GetMapping("/article/{id}")
    public String showArticleById(@PathVariable Long id, Model model) {
        CurrentEvents article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        model.addAttribute("images", article.getImages());
        return "articleDetails"; // Предположим, что у вас есть отдельное представление для отображения деталей объявления
    }


}
