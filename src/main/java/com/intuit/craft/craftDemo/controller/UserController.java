package com.intuit.craft.craftDemo.controller;

import com.intuit.craft.craftDemo.configuration.AuthConfiguration;
import com.intuit.craft.craftDemo.dto.LogInParams;
import com.intuit.craft.craftDemo.entity.User;
import com.intuit.craft.craftDemo.exception.UserDataException;
import com.intuit.craft.craftDemo.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthConfiguration authConfiguration;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody User user) throws UserDataException, DuplicateKeyException {

        try{
            String token = authenticationService.signup(user);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setBearerAuth(token);
            return ResponseEntity.ok(responseHeaders);
        }
        catch(org.springframework.dao.DuplicateKeyException e){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("USERNAME EXIST");
        }
        catch (Exception e){
            throw new UserDataException(e.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LogInParams logInParams) throws  UserDataException {

        try{
            logger.info("STARTED AUTHENTICATION PROCESS");
            String token = authenticationService.login(logInParams);
            if (token != null) {
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.setBearerAuth(token);
                return ResponseEntity.ok(responseHeaders);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INVALID CREDENTIALS");
        }

        catch(Exception e){
            throw new UserDataException(e.getMessage());
        }
    }
}
