package com.spiczek.mongo.example.domain.author;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Piotr Siczek
 */
@Getter
@Document
public class Author {

    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String otherStuff;

    public Author(String name, String surname, String email, String otherStuff) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.otherStuff = otherStuff;
    }
}