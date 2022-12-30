package com.ecommerce.repository;

import com.ecommerce.model.AuthenticationToken;
import com.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer> {

AuthenticationToken findByUser(User user);
}
