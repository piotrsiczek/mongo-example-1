package com.spiczek.mongo.example.domain.article;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Piotr Siczek
 */
@Getter
@AllArgsConstructor
public class AuthorShort {

    private String id;
    private String authorName;
    private String authorSurname;
}