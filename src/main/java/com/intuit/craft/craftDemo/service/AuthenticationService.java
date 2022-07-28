package com.intuit.craft.craftDemo.service;

import com.intuit.craft.craftDemo.dto.LogInParams;
import com.intuit.craft.craftDemo.entity.User;
import com.intuit.craft.craftDemo.exception.UserDataException;
import org.springframework.dao.DuplicateKeyException;

public interface AuthenticationService {

    String signup(User user) throws UserDataException;

    String login(LogInParams logInParams) throws DuplicateKeyException, UserDataException;
    User findUserByUserName(String username);

    User findUserByUserId(String userId);
}
