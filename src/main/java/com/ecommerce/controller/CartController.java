package com.ecommerce.controller;

import com.ecommerce.common.ApiResponse;
import com.ecommerce.dto.cart.AddToCartDto;
import com.ecommerce.dto.cart.CartDto;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.model.User;
import com.ecommerce.service.AuthenticationService;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    AuthenticationService authenticationService;

    //post cart api
    @PostMapping("/add")
    public ResponseEntity<ApiResponse>addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token) throws AuthenticationException, ProductNotFoundException {

        //authenticate the token
        authenticationService.authenticate(token);

        //find the user
        User user=authenticationService.getUserByToken(token);
        cartService.addToCart(addToCartDto,user);

        return new ResponseEntity<>(new ApiResponse(true,"Added to cart successfully"), HttpStatus.CREATED);

    }
    @GetMapping("/cart-items/{token}")
    //get all cart item for a user
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthenticationException {
        //authenticate the token
        authenticationService.authenticate(token);
        //find the user
        User user=authenticationService.getUserByToken(token);
        CartDto allCartItems = cartService.getAllCartItems(user);

        return new ResponseEntity<>(allCartItems,HttpStatus.OK);

    }
    //delete a cart item for a user
    @DeleteMapping("/delete-cart-items/{cartId}")
    public ResponseEntity<ApiResponse>deleteCartItems(@RequestParam("token") String token,@PathVariable Integer cartId) throws AuthenticationException {
        //authenticate the token
        authenticationService.authenticate(token);
        //find the user
        User user=authenticationService.getUserByToken(token);

        cartService.deleteCartItems(cartId,user);

        return new ResponseEntity<>(new ApiResponse(true,"Successfully deleted cart item"),HttpStatus.OK);


    }
}
