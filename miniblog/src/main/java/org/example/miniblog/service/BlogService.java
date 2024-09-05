package org.example.miniblog.service;
import org.example.miniblog.domain.Article;
import org.example.miniblog.domain.Comment;
import java.util.Collection;
import java.util.Optional;
public interface BlogService {
    Article saveArticle(Article article);
    Optional<Article> findArticleById(Long id);
    Collection<Article> allArticles();
    Comment saveComment(Comment comment);
}
