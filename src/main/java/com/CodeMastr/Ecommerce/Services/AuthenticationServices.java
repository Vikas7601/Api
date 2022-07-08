package com.CodeMastr.Ecommerce.Services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CodeMastr.Ecommerce.Exceptions.AuthenticationFailException;
import com.CodeMastr.Ecommerce.Models.AuthenticationToken;
import com.CodeMastr.Ecommerce.Models.Users;
import com.CodeMastr.Ecommerce.Repository.TokenRepo;

@Service
public class AuthenticationServices {
	
	@Autowired
	TokenRepo tokenRepo;
	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		tokenRepo.save(authenticationToken);	
	}
	
	public AuthenticationToken getToken(Users user) {
		return tokenRepo.findByUser(user);
	}
	
	public Users getUser(String token) {
		 final AuthenticationToken authenticationtoken = tokenRepo.findByToken(token);
		 if(Objects.isNull(token)) {
			 return null;
		 }
		 //token is  not null
		 return authenticationtoken.getUser();
		 
	}

	public void authenticate(String token) throws AuthenticationFailException {
		if(Objects.isNull(token)) {
			throw new AuthenticationFailException("token is not present");
			}
		if(Objects.isNull(getUser(token))) {
			throw new AuthenticationFailException("token not valid");
		}
		
	}

}
