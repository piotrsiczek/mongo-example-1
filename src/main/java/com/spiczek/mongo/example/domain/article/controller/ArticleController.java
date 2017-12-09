package com.spiczek.mongo.example.domain.article.controller;

import com.spiczek.mongo.example.domain.article.Article;
import com.spiczek.mongo.example.domain.article.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Piotr Siczek
 */
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/page/{page}/size{size}")
    public ArticlePage getArticlesWithPage(@PathVariable int page, @PathVariable int size) {
        Page<Article> result = articleRepository.findAll(new PageRequest(page, size));
        return new ArticlePage(result.getContent(), result.getTotalElements(), result.getTotalPages());
    }
}