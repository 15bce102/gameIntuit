package com.intuit.craft.craftDemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

@Document(collection = "games")
@Data
public class Game implements Serializable {
    @Id
    private final String gameId;
    @Indexed
    @Min(value = 0, message = "Score cannot be less than 0!")
    private final int score;
    private String userId;
    private Date createdAt = new Date();

}