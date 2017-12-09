package com.spiczek.mongo.example.domain.article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Getter
@Document
@NoArgsConstructor
public class Article implements Serializable {

    @Id
    private String id;
    private String title;
    private String text;
    private Date time;
    private List<Keyword> keywords;
    private AuthorShort author;

    public Article(String title, String text, Date time, List<Keyword> keywords, AuthorShort author) {
        this.title = title;
        this.text = text;
        this.time = time;
        this.keywords = keywords;
        this.author = author;
    }
}