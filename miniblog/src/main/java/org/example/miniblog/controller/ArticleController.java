package org.example.miniblog.controller;

import org.example.miniblog.domain.Article;
import org.example.miniblog.domain.Comment;
import org.example.miniblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Optional;

@Controller
public class ArticleController {
    @Autowired
    private BlogService blogService;
    @GetMapping(value="/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        Optional<Article> opArticle=blogService.findArticleById(id);
        if (opArticle.isPresent()) {
            model.addAttribute("article", opArticle.get());
            Comment comment = new Comment();
            model.addAttribute("comment", comment);
            return "article";
        } else {
            return "hello";
        }
    }
    @GetMapping(value = "/")
    public String getMethodName(Model model){
        Collection<Article> articles=blogService.allArticles();
        model.addAttribute("articles",articles);
        return  "all-articles";
    }
    @PostMapping(value = "/articles/{articleId}/comments")
    public String addComment(@PathVariable Long articleId, Comment comment) {
        Optional<Article> opArticle = blogService.findArticleById(articleId);
        if (opArticle.isPresent()) {
            comment.setArticle(opArticle.get());
            blogService.saveComment(comment);
            return "redirect:/articles/" + articleId;
        } else {
            return "hello";
        }
    }
}