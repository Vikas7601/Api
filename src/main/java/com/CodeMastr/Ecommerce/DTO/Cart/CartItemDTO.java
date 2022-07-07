package com.CodeMastr.Ecommerce.DTO.Cart;

import com.CodeMastr.Ecommerce.Models.Cart;
import com.CodeMastr.Ecommerce.Models.Products;

public class CartItemDTO {
	private Integer id;
	private Integer quantity;
	private Products products;
	
	
	public CartItemDTO() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	public CartItemDTO(Cart cart) {
		this.id = cart.getId();
		this.quantity = cart.getQuantity();
		this.setProducts(cart.getProducts());
	}
}
