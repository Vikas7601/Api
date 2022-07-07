package com.CodeMastr.Ecommerce.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CodeMastr.Ecommerce.DTO.ProductDTO;
import com.CodeMastr.Ecommerce.Models.Products;
import com.CodeMastr.Ecommerce.Models.Users;
import com.CodeMastr.Ecommerce.Models.Wishlist;
import com.CodeMastr.Ecommerce.Services.AuthenticationServices;
import com.CodeMastr.Ecommerce.Services.WishListServices;
import com.CodeMastr.Ecommerce.common.ApiResponse;

@RequestMapping("/wishlist")
@RestController
public class WishListController {
	@Autowired
	WishListServices wishListServices;

	@Autowired
	AuthenticationServices authenticationServices;

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToWishList(@RequestBody Products products,
			@RequestParam("token") String token) {
		// authenticate the token
		authenticationServices.authenticate(token);

		// find the user
		Users user = authenticationServices.getUser(token);

		// save the item to wishlist
		Wishlist wishlist = new Wishlist(user, products);
		wishListServices.createWishList(wishlist);

		ApiResponse apiResponse = new ApiResponse(true, "Added to WishList");
		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{token}")
	public ResponseEntity<List<ProductDTO>> getWishList(@PathVariable("token") String token){
		// authenticate the token
				authenticationServices.authenticate(token);

				// find the user
				Users user = authenticationServices.getUser(token);
				List<ProductDTO> productDTO = wishListServices.getWishListForUser(user);
				return new ResponseEntity<>(productDTO,HttpStatus.OK);
	}

}
