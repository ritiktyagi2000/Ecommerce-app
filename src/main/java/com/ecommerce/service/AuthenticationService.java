package com.ecommerce.service;

import com.ecommerce.model.AuthenticationToken;
import com.ecommerce.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
TokenRepository tokenRepository;
    public void saveToken(AuthenticationToken token) {

        tokenRepository.save(token);


    }
}
