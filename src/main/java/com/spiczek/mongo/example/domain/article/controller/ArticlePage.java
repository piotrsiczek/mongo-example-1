package com.spiczek.mongo.example.domain.article.controller;

import com.spiczek.mongo.example.domain.article.Article;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author Piotr Siczek
 */
@AllArgsConstructor
public class ArticlePage {
    private final List<Article> content;
    private final long totalElements;
    private final int totalPages;
}