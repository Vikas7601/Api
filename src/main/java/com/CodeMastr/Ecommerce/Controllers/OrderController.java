package com.CodeMastr.Ecommerce.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CodeMastr.Ecommerce.DTO.checkout.CheckoutItemDTO;
import com.CodeMastr.Ecommerce.DTO.checkout.StripeResponse;
import com.CodeMastr.Ecommerce.Services.AuthenticationServices;
import com.CodeMastr.Ecommerce.Services.OrderServices;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;


@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private AuthenticationServices authenticationServices;
	
	@Autowired
	private OrderServices orderServices;
	
	//stripe session checkout  api
	
	@PostMapping("/create-checkout-session")
	public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDTO> checkoutItemDTOList )
				throws StripeException{
		Session session=orderServices.createSession(checkoutItemDTOList);
		StripeResponse stripeResponse=new StripeResponse(session.getId());
		return new ResponseEntity<>(stripeResponse,HttpStatus.OK);
	}
}
