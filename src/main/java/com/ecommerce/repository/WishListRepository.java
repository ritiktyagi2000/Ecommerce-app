package com.ecommerce.repository;

import com.ecommerce.model.User;
import com.ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList,Integer> {


    WishList findByUser(User user);
    List<WishList>findAllByUserOrderByCreatedDateDesc(User user);
}
