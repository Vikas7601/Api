package com.CodeMastr.Ecommerce.Models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="wishlist")
public class Wishlist {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id" )
    private Users user;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Products products;

	public Wishlist(Integer id, Users user, Date created_date, Products products) {
		this.id = id;
		this.user = user;
		this.createdDate = created_date;
		this.products = products;
	}

	public Wishlist() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Date getCreated_date() {
		return createdDate;
	}

	public void setCreated_date(Date created_date) {
		this.createdDate = created_date;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public Wishlist(Users user, Products products) {
		this.createdDate=new Date();
		this.user = user;
		this.products = products;
		
		
	}
	
	
}
