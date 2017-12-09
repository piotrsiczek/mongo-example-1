package com.spiczek.mongo.example.domain.author.repository;

import com.spiczek.mongo.example.domain.author.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Piotr Siczek
 */
@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

}