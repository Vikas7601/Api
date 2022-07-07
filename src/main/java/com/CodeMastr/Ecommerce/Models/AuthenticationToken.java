package com.CodeMastr.Ecommerce.Models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="tokens")
public class AuthenticationToken {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="token")
	private String token;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@OneToOne(targetEntity=Users.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="user_id")
	private Users user;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public AuthenticationToken(Users user) {
		
		this.user = user;
		this.createdDate=new Date();
		this.token=UUID.randomUUID().toString();
	}

	public AuthenticationToken() {
	}
	
	



}
