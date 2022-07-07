package com.CodeMastr.Ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CodeMastr.Ecommerce.Models.Users;
import com.CodeMastr.Ecommerce.Models.Wishlist;

@Repository
public interface WishListRepo extends JpaRepository<Wishlist, Integer> {
	List<Wishlist> findAllByUserOrderByCreatedDateDesc(Users user);
}
