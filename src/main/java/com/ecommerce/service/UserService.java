package com.ecommerce.service;

import com.ecommerce.dto.user.ResponseDto;
import com.ecommerce.dto.user.SignUpDto;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public ResponseDto signup(SignUpDto signUpDto) {
       ResponseDto responseDto=new ResponseDto("success","dummy response");
       //check if user is already present
        if(Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))){
            //we have to throw an exception
        }


       //save the user
        //hash the password
        //create the token
       return responseDto;
    }
}
