package com.ecommerce.controller;

import com.ecommerce.dto.user.ResponseDto;
import com.ecommerce.dto.user.SignUpDto;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    //sign up
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignUpDto signUpDto) throws NoSuchAlgorithmException {
        return userService.signup(signUpDto);
    }
}
