package org.example.miniblog.controller;

import org.example.miniblog.domain.Article;
import org.example.miniblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AuthorController {
    private  static Logger logger = LoggerFactory.getLogger(AuthorController.class);
    @GetMapping("/author/articles/new")
    public String newArticle(Model model) {
        Article article = new Article();
        model.addAttribute("article", article);
        return "new-article";
    }
    @Autowired
    private BlogService blogService;
    @PostMapping(value = "/author/articles")
    public String createArticle(Article article) {
        logger.info("Article title: " + article.getTitle());
        Article savedArticle= blogService.saveArticle(article);
        return "redirect:/articles/"+savedArticle.getId();
    }
}