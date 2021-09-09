package com.shopping.onlineshopping.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.onlineshopping.user.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String>{

}
