package com.ecommerce.service;

import com.ecommerce.model.AuthenticationToken;
import com.ecommerce.model.User;
import com.ecommerce.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.Objects;

@Service
public class AuthenticationService {

    @Autowired
TokenRepository tokenRepository;
    public void saveToken(AuthenticationToken token) {

        tokenRepository.save(token);


    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }

    public User getUserByToken(String token){
        final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
                if(Objects.isNull(authenticationToken)){
                return null;
                }
                //authentication token is not null
        return authenticationToken.getUser();

    }

    public void authenticate(String token) throws AuthenticationException {
        //null check

        if(Objects.isNull(token)){
            //throw an exception
        throw new AuthenticationException("Token is not present");
        }
        User user = getUserByToken(token);
        if(Objects.isNull(user)){
            throw new AuthenticationException("token not valid");
        }

    }
}
