package com.intuit.craft.craftDemo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.intuit.craft.craftDemo.dto.LogInParams;
import com.intuit.craft.craftDemo.entity.User;
import com.intuit.craft.craftDemo.exception.UserDataException;
import com.intuit.craft.craftDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.intuit.craft.craftDemo.constants.Constants.EXPIRATION_TIME;
import static com.intuit.craft.craftDemo.constants.Constants.SECRET;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String signup(User user) throws DuplicateKeyException, UserDataException  {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user = this.userRepository.save(user);
            return createAuthToken(user.getUserId());
        }
        catch(org.springframework.dao.DuplicateKeyException e){
            throw new DuplicateKeyException(e.getMessage());
        }
        catch(Exception e){
            throw new UserDataException(e.getMessage());
        }
    }

    @Override
    public String login(LogInParams logInParams) throws DuplicateKeyException, UserDataException {
        try{
            User user = findUserByUserName(logInParams.getUsername());
            if(user==null){
                return null;
            }
            String token = createAuthToken(user.getUserId());
            if (passwordEncoder.matches(logInParams.getPassword(), user.getPassword())){
                return token;
            }
            return null;
        }
        catch(org.springframework.dao.DuplicateKeyException e){
            throw new DuplicateKeyException(e.getMessage());
        }
        catch (Exception e){
            throw new UserDataException(e.getMessage());
        }
    }


    private String createAuthToken(String userId) {
        return JWT.create()
                .withSubject(userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findUserByUserName(username);
    }

    @Override
    public User findUserByUserId(String userId){
        return userRepository.findUserByUserId(userId);
    }
}

