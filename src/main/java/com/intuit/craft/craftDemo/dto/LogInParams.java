package com.intuit.craft.craftDemo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class LogInParams {
    @NotEmpty(message = "Username cannot be empty!")
    private String username;
    private String password;
}