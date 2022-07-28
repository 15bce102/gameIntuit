package com.intuit.craft.craftDemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Document(collection = "users")
@Data
public class User {
    @Id
    private final String userId;
    @Indexed(unique = true)
    @NotEmpty(message = "Username cannot be empty!")
    private final String username;
    @Indexed(unique = true)
    @Email(message = "Not a valid email!")
    private final String email;
    @Size(min = 6, message = "Min length of password is 6!")
    private String password;
    private final String profileImageUrl;
}
