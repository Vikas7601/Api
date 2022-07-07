package com.CodeMastr.Ecommerce.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodeMastr.Ecommerce.DTO.ResponseDTO;
import com.CodeMastr.Ecommerce.DTO.Users.SignInDTO;
import com.CodeMastr.Ecommerce.DTO.Users.SignInResponseDTO;
import com.CodeMastr.Ecommerce.DTO.Users.SignUpDTO;
import com.CodeMastr.Ecommerce.Services.UserServices;

@RequestMapping("user")
@RestController
public class UserController {
	
	@Autowired
	private UserServices userServices;

	@PostMapping("/signUp")
	public ResponseDTO signup(@RequestBody SignUpDTO  signupDTO) {
		return userServices.signUp(signupDTO);
	}
	
	@PostMapping("/signIn")
	public SignInResponseDTO  signIn(@RequestBody SignInDTO signInDTO) {
		return userServices.signIn(signInDTO);
	}
	
	
	
	
	
}


