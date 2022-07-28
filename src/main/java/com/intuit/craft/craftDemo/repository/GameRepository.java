package com.intuit.craft.craftDemo.repository;

import com.intuit.craft.craftDemo.entity.Game;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface GameRepository extends MongoRepository<Game, String> {
    @Aggregation(pipeline = {
            "{ '$sort' : { 'score' : -1 } }",
            "{ '$limit' : ?0 }"
    })
    List<Game> findTopKScores(int limit);
}
