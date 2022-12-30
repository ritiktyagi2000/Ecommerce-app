package com.ecommerce.controller;

import com.ecommerce.common.ApiResponse;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.model.WishList;
import com.ecommerce.service.AuthenticationService;
import com.ecommerce.service.WishListService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    WishListService wishListService;
    @Autowired
    AuthenticationService authenticationService;


    //save product into wishlist
    @PostMapping("/save/")
    public ResponseEntity<ApiResponse> savetoWishList(@RequestBody Product product, @RequestParam("token") String token  ) throws AuthenticationException {


        //authenticate the token
        authenticationService.authenticate(token);

        //find the user
        User user=authenticationService.getUserByToken(token);

        //save the item in wishlist
        WishList wishList=new WishList(user,product);

        wishListService.save(wishList);
        return new ResponseEntity<>(new ApiResponse(true,"Added to wishlist"), HttpStatus.OK);
    }

    //get all product from wishlist
    @GetMapping("/products/{token}")
    public List<ProductDto>getAllProduct(@PathVariable String token) throws AuthenticationException {
        //authenticate the token
        authenticationService.authenticate(token);

        //find the user
        User user=authenticationService.getUserByToken(token);


        List<ProductDto> allProducts = wishListService.getAllProducts(user);

       return allProducts;
    }
    @DeleteMapping("/delete/{token}/{wishlistId}")
    public ResponseEntity<ApiResponse>deleteWishList(@PathVariable String token,@PathVariable Integer wishlistId) throws AuthenticationException {
        //authenticate the token
        authenticationService.authenticate(token);

        //find the user
        User user=authenticationService.getUserByToken(token);
        ApiResponse deleteWishlist = wishListService.deleteWishlist(wishlistId);

        return new ResponseEntity<>(deleteWishlist,HttpStatus.OK);

    }



}
