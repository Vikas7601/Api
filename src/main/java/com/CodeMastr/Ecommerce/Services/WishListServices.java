package com.CodeMastr.Ecommerce.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CodeMastr.Ecommerce.DTO.ProductDTO;
import com.CodeMastr.Ecommerce.Models.Users;
import com.CodeMastr.Ecommerce.Models.Wishlist;
import com.CodeMastr.Ecommerce.Repository.WishListRepo;

@Service
public class WishListServices {
	@Autowired
	WishListRepo wishListRepo;
	
	@Autowired
	ProductServices productServices;

	public void createWishList(Wishlist wishlist) {
		wishListRepo.save(wishlist);
		
	}

	public List<ProductDTO> getWishListForUser(Users user) {
		 final List<Wishlist> wishLists=wishListRepo.findAllByUserOrderByCreatedDateDesc(user);
		 List<ProductDTO> productDTO = new ArrayList<>();
		 for(Wishlist wishList: wishLists) {
			 productDTO.add(productServices.getProductDTO(wishList.getProducts()));
		 }
		 return productDTO;
				 }
}
