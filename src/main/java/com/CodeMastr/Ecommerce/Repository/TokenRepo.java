package com.CodeMastr.Ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CodeMastr.Ecommerce.Models.AuthenticationToken;
import com.CodeMastr.Ecommerce.Models.Users;
@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {
	AuthenticationToken findByUser(Users user);
	AuthenticationToken findByToken(String token);

	
}
