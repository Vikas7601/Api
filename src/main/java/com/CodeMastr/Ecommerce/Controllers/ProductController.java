package com.CodeMastr.Ecommerce.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.CodeMastr.Ecommerce.DTO.ProductDTO;
import com.CodeMastr.Ecommerce.Models.Category;
import com.CodeMastr.Ecommerce.Repository.CategoryRepo;
import com.CodeMastr.Ecommerce.Services.ProductServices;
import com.CodeMastr.Ecommerce.common.ApiResponse;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductServices productServices;
	
	@Autowired 
	CategoryRepo categoryRepo;
	
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO){
		Optional<Category> optionalCategory=categoryRepo.findById(productDTO.getCategoryId());
		if(!optionalCategory.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category not present"),HttpStatus.BAD_REQUEST);
		}
		productServices.createProduct(productDTO,optionalCategory.get());
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<ProductDTO>> getProducts(){
	   List<ProductDTO> products=productServices.getAllProducts();
	   return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId ,@RequestBody ProductDTO productDTO) throws Exception{
		Optional<Category> optionalCategory=categoryRepo.findById(productDTO.getCategoryId());
		if(!optionalCategory.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Product is not present"),HttpStatus.BAD_REQUEST);
		}
		productServices.updateProduct(productDTO,productId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"),HttpStatus.OK);
		
	}
	
	
}
	
	

