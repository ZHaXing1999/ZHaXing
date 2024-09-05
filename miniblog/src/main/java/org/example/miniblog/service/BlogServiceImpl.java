package org.example.miniblog.service;
import org.example.miniblog.domain.Article;
import org.example.miniblog.domain.Comment;
import org.example.miniblog.repository.ArticleRepository;
import org.example.miniblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public Article saveArticle(Article article) {
        Article savedArticle = articleRepository.save(article);
        return savedArticle;
    }
    @Override
    public Optional<Article> findArticleById(Long id) {
        Optional<Article> opArticle = articleRepository.findById(id);
        return opArticle;
    }
    @Override
    public Collection<Article> allArticles(){
        Collection<Article> articles=articleRepository.findAll();
        return articles;
    }
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}