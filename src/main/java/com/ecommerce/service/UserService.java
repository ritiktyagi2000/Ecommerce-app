package com.ecommerce.service;

import com.ecommerce.dto.user.ResponseDto;
import com.ecommerce.dto.user.SignUpDto;
import com.ecommerce.exception.CustomException;
import com.ecommerce.model.AuthenticationToken;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;
    @Transactional
    public ResponseDto signup(SignUpDto signUpDto) throws NoSuchAlgorithmException {
        //check if user is already present
        if (Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))) {
            //we have to throw an exception
            throw new CustomException("User already present,Please Sign in..");
        }
        //hash the password
        String encryptedPassword = signUpDto.getPassword();
        try {
            encryptedPassword = hashPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //  //save the user
        User user=new User();
        user.setEmail(signUpDto.getEmail());
        user.setFirstName(signUpDto.getFirstName());
        user.setPassword(encryptedPassword);
        user.setLastName(signUpDto.getLastName());
        userRepository.save(user);



        //create the token
        final AuthenticationToken token=new AuthenticationToken(user);
        authenticationService.saveToken(token);

        ResponseDto responseDto = new ResponseDto("success", "User created successfully");
        return responseDto;
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        // Add password bytes to digest
        md.update(password.getBytes());

        // Get the hash's bytes
        byte[] bytes = md.digest();

        // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        // Get complete hashed password in hex format
       String hashedPassword = sb.toString();
        return hashedPassword;
    }
}
