package com.spiczek.mongo.example;

import com.spiczek.mongo.example.domain.article.Article;
import com.spiczek.mongo.example.domain.article.ArticleService;
import com.spiczek.mongo.example.domain.article.AuthorShort;
import com.spiczek.mongo.example.domain.article.Keyword;
import com.spiczek.mongo.example.domain.article.repository.ArticleRepository;
import com.spiczek.mongo.example.domain.author.Author;
import com.spiczek.mongo.example.domain.author.repository.AuthorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoApplicationTests {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    ArticleService articleService;

    private List<Article> articles;

    @Before
    public void init() {
        articleRepository.deleteAll();
        authorRepository.deleteAll();

        List<Author> authors = Stream.of(
                new Author(TestConstants.AUTHOR_NAME_1, TestConstants.AUTHOR_SURNAME_1, TestConstants.AUTHOR_EMAIL_1, TestConstants.AUTHOR_OTHER_STUFF),
                new Author(TestConstants.AUTHOR_NAME_2, TestConstants.AUTHOR_SURNAME_2, TestConstants.AUTHOR_EMAIL_2, TestConstants.AUTHOR_OTHER_STUFF)
        ).collect(Collectors.toList());

        authors = authorRepository.save(authors);

        articles = Stream.of(
                new Article(TestConstants.TITLE_1, TestConstants.TEXT_1, new Date(), keywords(TestConstants.KEYWORD_SPORT, TestConstants.KEYWORD_SPORT_CLIMBING), authorShort(authors.get(1))),
                new Article(TestConstants.TITLE_2, TestConstants.TEXT_2, new Date(), keywords(TestConstants.KEYWORD_SPORT, TestConstants.KEYWORD_SPORT_FOOTBALL), authorShort(authors.get(0))),
                new Article(TestConstants.TITLE_3, TestConstants.TEXT_3, new Date(), keywords(TestConstants.KEYWORD_TECHNOLOGY, TestConstants.KEYWORD_TECHNOLOGY_SPRING,
                        TestConstants.KEYWORD_TECHNOLOGY_SPRING_BOOT, TestConstants.KEYWORD_TECHNOLOGY_SPRING_CLOUD), authorShort(authors.get(0))),
                new Article(TestConstants.TITLE_4, TestConstants.TEXT_4, new Date(), keywords(TestConstants.KEYWORD_TECHNOLOGY, TestConstants.KEYWORD_TECHNOLOGY_SPRING,
                        TestConstants.KEYWORD_TECHNOLOGY_SPRING_BOOT), authorShort(authors.get(1))),
                new Article(TestConstants.TITLE_5, TestConstants.TEXT_5, new Date(), keywords(TestConstants.KEYWORD_TECHNOLOGY, TestConstants.KEYWORD_TECHNOLOGY_SPRING,
                        TestConstants.KEYWORD_TECHNOLOGY_SPRING_BOOT), authorShort(authors.get(0))),
                new Article(TestConstants.TITLE_6, TestConstants.TEXT_6, new Date(), null, authorShort(authors.get(0)))
        ).collect(Collectors.toList());

        articleRepository.save(articles);
    }

    private List<Keyword> keywords(Keyword... keywords) {
        return asList(keywords);
    }

    private AuthorShort authorShort(Author author) {
        return new AuthorShort(author.getId(), author.getName(), author.getSurname());
    }

    @Test
    public void getAllArticlesTest() {
        assertTrue(articleRepository.findAll().size() == articles.size());
    }

    @Test
    public void getArticlesPageTest() {
        List<Article> result = articleRepository.findAll(new PageRequest(1, 2)).getContent();
        assertTrue(result.size() == 2);
        assertTrue(result.get(0).getId().equals(articles.get(2).getId()));
        assertTrue(result.get(1).getId().equals(articles.get(3).getId()));
    }

    @Test
    public void updateArticleTitleByIdTest() {
        String title = "new title";
        String id = articles.get(5).getId();
        assertTrue(articleService.updateTitleById(id, title));
        assertTrue(articleRepository.findOne(id).getTitle().equals(title));
    }

}