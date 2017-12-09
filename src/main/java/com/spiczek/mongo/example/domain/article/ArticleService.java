package com.spiczek.mongo.example.domain.article;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author Piotr Siczek
 */
@Service
public class ArticleService {

    private MongoTemplate mongo;

    @Autowired
    public ArticleService(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    public boolean updateTitleById(String articleId, String title) {
        return mongo.updateFirst(query(where("_id").is(new ObjectId(articleId))), new Update().set("title", title), Article.class).getN() > 0;
    }
}