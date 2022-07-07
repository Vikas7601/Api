package com.CodeMastr.Ecommerce.Exceptions;

public class ProductNotExistException extends IllegalArgumentException {
	public ProductNotExistException(String msg ) {
		super(msg);
	}
}
