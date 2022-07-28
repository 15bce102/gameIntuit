package com.intuit.craft.craftDemo.repository;

import com.intuit.craft.craftDemo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{username : ?0}")
    User findUserByUserName(String username);

    @Query("{userId : ?0}")
    User findUserByUserId(String userId);
}