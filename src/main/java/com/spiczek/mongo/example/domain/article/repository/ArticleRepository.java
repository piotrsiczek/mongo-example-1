package com.spiczek.mongo.example.domain.article.repository;

import com.spiczek.mongo.example.domain.article.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Piotr Siczek
 */
@Repository
public interface ArticleRepository extends MongoRepository<Article, String>, PagingAndSortingRepository<Article, String> {

}