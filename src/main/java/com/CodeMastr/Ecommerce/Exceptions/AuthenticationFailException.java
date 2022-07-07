package com.CodeMastr.Ecommerce.Exceptions;

public class AuthenticationFailException  extends IllegalArgumentException {
	 public AuthenticationFailException(String msg) {
		 super(msg);
	 }
}
