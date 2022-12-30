package com.ecommerce.service;

import com.ecommerce.dto.cart.AddToCartDto;
import com.ecommerce.dto.cart.CartDto;
import com.ecommerce.dto.cart.CartItemDto;
import com.ecommerce.exception.CustomException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    @Autowired
    ProductService productService;
    @Autowired
    CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, User user) throws ProductNotFoundException {

        Product product = productService.findByProductId(addToCartDto.getProductId());
        Cart cart=new Cart();
        cart.setCreatedDate(new Date());
        cart.setProduct(product);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setUser(user);
        cartRepository.save(cart);

    }

    public CartDto getAllCartItems(User user) {
        List<Cart> cartList = cartRepository.findByUser(user);

        List<CartItemDto> cartItemDtoList=new ArrayList<>();
        double totalcost=0;

        for(Cart cart:cartList){

            CartItemDto cartItemDto=new CartItemDto();
            cartItemDto.setId(cart.getId());
            cartItemDto.setProduct(cart.getProduct());
            cartItemDto.setQuantity(cart.getQuantity());
            cartItemDtoList.add(cartItemDto);

            double price=cart.getProduct().getPrice();
            totalcost=totalcost+price*cart.getQuantity();
        }

        CartDto cartDto=new CartDto();
        cartDto.setCartItemDtoList(cartItemDtoList);
        cartDto.setTotalCost(totalcost);
        return cartDto;

    }

    public void deleteCartItems(Integer cartId, User user) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if(optionalCart.isEmpty()){
            throw  new CustomException("Cart item does not exist..");
        }
        Cart cart = optionalCart.get();
        if(cart.getUser().getId()!=user.getId()){
            throw new CustomException("Cart item does not belong to this user: "+cartId);
        }
        cartRepository.delete(cart);


    }
}
