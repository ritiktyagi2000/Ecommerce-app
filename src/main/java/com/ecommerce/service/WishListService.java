package com.ecommerce.service;

import com.ecommerce.common.ApiResponse;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.exception.CustomException;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.model.WishList;
import com.ecommerce.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WishListService {
    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    ProductService productService;

    public void save(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public WishList getWishList(User user) {
        WishList wishList = wishListRepository.findByUser(user);
        return wishList;
    }


    public List<ProductDto> getAllProducts(User user) {

        List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtoList=new ArrayList<>();
        for(WishList wishList:wishLists){

            Product product = wishList.getProduct();
            ProductDto productDto = productService.productToDto(product);
        productDtoList.add(productDto);
        }
        return productDtoList;

    }

    public ApiResponse deleteWishlist(Integer wishlistId) throws CustomException {
        Optional<WishList> wishList = wishListRepository.findById(wishlistId);
        if(Objects.isNull(wishList.get())){
            throw new CustomException("Not any wishlist product found");
        }
        wishListRepository.delete(wishList.get());


        return new ApiResponse(true,"Deleted Successfully");
    }
}
