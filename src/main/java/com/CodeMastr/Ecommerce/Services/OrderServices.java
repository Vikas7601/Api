package com.CodeMastr.Ecommerce.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.CodeMastr.Ecommerce.DTO.checkout.CheckoutItemDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;


@Service
public class OrderServices {
	
	@Value("${BASE_URL}")
	private String baseURL;
	
	@Value("${STRIPE_SECRET_KEY}")
	private String apiKey;
	
	public Session createSession(List<CheckoutItemDTO> checkoutItemDTOList) throws StripeException {
		//success and failure urls
		String successURL = baseURL + "payment/success";
		
		String failureURL = baseURL + "payment/failure";
		 
		Stripe.apiKey=apiKey;
		
		List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();
		
		for(CheckoutItemDTO checkoutItemDTO: checkoutItemDTOList) {
			sessionItemList.add(createSessionLineItem(checkoutItemDTO));
		}
		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setCancelUrl(failureURL)
				.setSuccessUrl(successURL)
				.addAllLineItem(sessionItemList)
				.build();
		return Session.create(params);
				
	
	}
	
	private SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDTO checkoutItemDTO) {
		return SessionCreateParams.LineItem.builder()
				.setPriceData(createPriceData(checkoutItemDTO))
				.setQuantity(Long.parseLong(String.valueOf(checkoutItemDTO.getQuantity())))
				.build();
		
	}

	private SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDTO checkoutItemDTO) {
		return SessionCreateParams.LineItem.PriceData.builder()
				.setCurrency("usd")
				.setUnitAmount((long)checkoutItemDTO.getPrice()*100)
				.setProductData(
						SessionCreateParams.LineItem.PriceData.ProductData.builder()
							.setName(checkoutItemDTO.getProductName())
							.build()
						).build();
	}
	

}
