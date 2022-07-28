package com.intuit.craft.craftDemo.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.intuit.craft.craftDemo.constants.Constants.*;

@Configuration
public class AuthConfiguration {

    Logger logger = LoggerFactory.getLogger(AuthConfiguration.class);
    @Bean
    public Filter authFilter() {
        return (request, response, chain) -> {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            logger.info("AuthConfiguration "+httpRequest.getMethod()+" "+httpRequest.getServletPath());
            if ((httpRequest.getServletPath().equals("/users/signup") || httpRequest.getServletPath().equals("/users/login"))&& httpRequest.getMethod().equalsIgnoreCase("post")){
                chain.doFilter(request, response);
            }
            else{
                try{
                    String token = ((HttpServletRequest) request).getHeader(HEADER_STRING);
                    if(token!=null){
                        logger.info("Fetch token from header");
                        String userId = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                                .build()
                                .verify(token.replace(TOKEN_PREFIX, ""))
                                .getSubject();
                        logger.info(userId);
                        request.setAttribute("userId", userId);
                        if(userId!=null ){
                            chain.doFilter(request,response);
                        }
                        else
                            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
                    }
                    else
                        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
                }
                catch(Exception e){
                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
                }

            }

        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}