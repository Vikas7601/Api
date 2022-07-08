package com.CodeMastr.Ecommerce.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CodeMastr.Ecommerce.DTO.ProductDTO;
import com.CodeMastr.Ecommerce.DTO.Cart.AddToCartDTO;
import com.CodeMastr.Ecommerce.DTO.Cart.CartDTO;
import com.CodeMastr.Ecommerce.Models.Products;
import com.CodeMastr.Ecommerce.Models.Users;
import com.CodeMastr.Ecommerce.Services.AuthenticationServices;
import com.CodeMastr.Ecommerce.Services.CartService;
import com.CodeMastr.Ecommerce.common.ApiResponse;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/cart")
public class CartController {

	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private AuthenticationServices authenticationServices;
	

	
	//post cart api
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDTO addToCartDTO ,@RequestParam("token") String token){
		// authenticate the token
				authenticationServices.authenticate(token);

				// find the user
				Users user = authenticationServices.getUser(token); 
				cartService.addToCart(addToCartDTO, user);
				return new ResponseEntity<>(new ApiResponse(true,"Added to cart successfully"),HttpStatus.CREATED);
	}
	
	
	//get all cart items for a user
	@GetMapping("/getCartItems")
	public ResponseEntity<CartDTO> getCartItems(@RequestParam("token") String token){
		// authenticate the token
		authenticationServices.authenticate(token);

		// find the user
		Users user = authenticationServices.getUser(token); 
		//get cart items
		
		CartDTO cartDTO =cartService.listCartItems(user);
		return new ResponseEntity<>(cartDTO,HttpStatus.OK);
	}
	
	
	//delete a cart item for  a user
	@DeleteMapping("/delete/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer itemId,@RequestParam("token") String token){
		// authenticate the token
		authenticationServices.authenticate(token);

		// find the user
		Users user = authenticationServices.getUser(token);	
		cartService.deleteCartItem(itemId,user);
		return new ResponseEntity<>(new ApiResponse(true,"Item removed from cart successfully"),HttpStatus.OK);
		
	}
	
} 
