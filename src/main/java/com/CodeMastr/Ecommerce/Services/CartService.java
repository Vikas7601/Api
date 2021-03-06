package com.CodeMastr.Ecommerce.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CodeMastr.Ecommerce.DTO.Cart.AddToCartDTO;
import com.CodeMastr.Ecommerce.DTO.Cart.CartDTO;
import com.CodeMastr.Ecommerce.DTO.Cart.CartItemDTO;
import com.CodeMastr.Ecommerce.Exceptions.CustomException;
import com.CodeMastr.Ecommerce.Exceptions.ProductNotExistException;
import com.CodeMastr.Ecommerce.Models.Cart;
import com.CodeMastr.Ecommerce.Models.Products;
import com.CodeMastr.Ecommerce.Models.Users;
import com.CodeMastr.Ecommerce.Repository.CartRepo;

@Service

public class CartService {
	@Autowired
	ProductServices productServices;
	
	@Autowired
	private  CartRepo cartRepo;
	
	public void addToCart(AddToCartDTO addToCartDTO, Users user) throws ProductNotExistException {
		
		//validate if the product id is valid 
		Products products = productServices.findById(addToCartDTO.getProductId());
		
		Cart  cart =new Cart();
		cart.setProducts(products);
		cart.setUser(user);
		cart.setQuantity(addToCartDTO.getQuantity());
		cart.setCreatedDate(new Date());
		
		
		//save the cart
		cartRepo.save(cart);
	}
	public CartDTO listCartItems(Users user) {
		List<Cart> cartList = cartRepo.findAllByUserOrderByCreatedDateDesc(user);
		List<CartItemDTO> cartItems = new ArrayList<>();
		double totalCost = 0;
		for (Cart cart:cartList) {
			CartItemDTO cartItemDTO = new CartItemDTO(cart);
			totalCost += cartItemDTO.getQuantity() * cart.getProducts().getPrice();
			cartItems.add(cartItemDTO);
			
		}
		CartDTO cartDTO = new CartDTO() ;
		cartDTO.setTotalCost(totalCost);
		cartDTO.setCartItems(cartItems);
		return cartDTO;
		}
	
	public void deleteCartItem(Integer cartItemId, Users user) {
		//item id belongs to user
		Optional<Cart> optionalCart =cartRepo.findById(cartItemId);
		if(optionalCart.isPresent()) {
			throw new  CustomException("Cart Item Id is Invalid " + cartItemId );
		}
		Cart cart = optionalCart.get();
		if(cart.getUser() != user) {
			throw new CustomException("Cart Item  Id does not belongs to the User " + cartItemId);
		}
		cartRepo.delete(cart);
		
	}

}
