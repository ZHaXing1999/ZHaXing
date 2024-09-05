package org.example.miniblog.repository;

import org.example.miniblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
