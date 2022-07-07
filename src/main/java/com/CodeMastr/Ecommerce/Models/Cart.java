package com.CodeMastr.Ecommerce.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
@Column(name="created_date")
 private Date  createdDate;


@ManyToOne
@JoinColumn(name="product_id")
private Products products;

@ManyToOne
@JoinColumn(name="user_id")
private Users user;

//@Column(name="quantity")
private int quantity;

public Cart() {
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Date getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}

public Products getProducts() {
	return products;
}

public void setProducts(Products products) {
	this.products = products;
}

public Users getUser() {
	return user;
}

public void setUser(Users user) {
	this.user = user;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
} 

}
