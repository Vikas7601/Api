package com.CodeMastr.Ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CodeMastr.Ecommerce.Models.Cart;
import com.CodeMastr.Ecommerce.Models.Users;

public interface CartRepo extends JpaRepository<Cart, Integer> {
	List<Cart> findAllByUserOrderByCreatedDateDesc(Users user);
}
