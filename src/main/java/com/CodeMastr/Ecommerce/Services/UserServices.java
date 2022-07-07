package com.CodeMastr.Ecommerce.Services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.aspectj.weaver.ast.HasAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CodeMastr.Ecommerce.DTO.ResponseDTO;
import com.CodeMastr.Ecommerce.DTO.Users.SignInDTO;
import com.CodeMastr.Ecommerce.DTO.Users.SignInResponseDTO;
import com.CodeMastr.Ecommerce.DTO.Users.SignUpDTO;
import com.CodeMastr.Ecommerce.Exceptions.AuthenticationFailException;
import com.CodeMastr.Ecommerce.Exceptions.CustomException;
import com.CodeMastr.Ecommerce.Models.AuthenticationToken;
import com.CodeMastr.Ecommerce.Models.Users;
import com.CodeMastr.Ecommerce.Repository.UserRepo;

@Service
public class UserServices {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private  AuthenticationServices authenticationServices;
	
	@Transactional
	// check the user is already present 
	public ResponseDTO signUp(SignUpDTO signupDTO) {
		if(Objects.nonNull(userRepo.findByEmail(signupDTO.getEmail()))) {
			throw new CustomException("User already present");
		}
		
		
		// hash the password
		String encryptedpassword =  signupDTO.getPassword();
		try {
			encryptedpassword = hashPassword(signupDTO.getPassword());
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();	
		}
		
		
		
		
		Users user = new Users(signupDTO.getFirstName(),signupDTO.getLastName(),signupDTO.getEmail(),encryptedpassword);
		userRepo.save(user);
		
		
		//create the token
		final AuthenticationToken authenticationToken = new AuthenticationToken(user);
		authenticationServices.saveConfirmationToken(authenticationToken);
		
		
		ResponseDTO responseDTO = new ResponseDTO("success", "User created successfully");
		return responseDTO;
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest =md.digest();
		String hash = DatatypeConverter
							.printHexBinary(digest).toUpperCase();
		return hash;	
	}

	public SignInResponseDTO signIn(SignInDTO signInDTO) {
		//find user by email
		Users user =userRepo.findByEmail(signInDTO.getEmail());
		if(Objects.isNull(user)) {
			throw new AuthenticationFailException("User not found");
		}
		
		//hash the password
		try {
			if (!user.getPassword().equals(hashPassword(signInDTO.getPassword()))) {
				throw new AuthenticationFailException("Invalid Password");
				}
			}catch(NoSuchAlgorithmException e){
				e.printStackTrace();
			}
			
	
				
		
		//if password matches
		 AuthenticationToken token = authenticationServices.getToken(user);
		
		
		//Retrieve the token
		if(Objects.isNull(token)) {
			throw new CustomException("Token is not present");
		}	
		return new SignInResponseDTO("success", token.getToken());
		//return response
		
	}
}
