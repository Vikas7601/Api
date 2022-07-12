package com.CodeMastr.Ecommerce.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CodeMastr.Ecommerce.DTO.ProductDTO;
import com.CodeMastr.Ecommerce.Exceptions.ProductNotExistException;
import com.CodeMastr.Ecommerce.Models.Category;
import com.CodeMastr.Ecommerce.Models.Products;
import com.CodeMastr.Ecommerce.Repository.ProductRepo;

@Service
public class ProductServices {
	
	@Autowired
	ProductRepo productRepo;
	
	public void createProduct(ProductDTO productDTO, Category category) {
		Products products =new Products();
		products.setName(productDTO.getName());
		products.setDescription(productDTO.getDescription());
		products.setCategory(category);
		products.setImageURL(productDTO.getImageURL());
		products.setPrice(productDTO.getPrice());
		productRepo.save(products);
		
		
	}
	
	public ProductDTO getProductDTO(Products products) {
		ProductDTO  productDTO =new ProductDTO();
		productDTO.setName(products.getName());
		productDTO.setDescription(products.getDescription());
		productDTO.setCategoryId(products.getCategory().getId());
		productDTO.setImageURL(products.getImageURL());
		productDTO.setPrice(products.getPrice());
		productDTO.setId(products.getId());
		return productDTO;
	}

	public List<ProductDTO> getAllProducts() {
		 List<Products> allProducts =productRepo.findAll();
		 List<ProductDTO> productDTOs=new ArrayList<>();
		 for (Products products:allProducts) {
			 productDTOs.add(getProductDTO(products));
		 }
		return productDTOs;
	}

	public void updateProduct(ProductDTO productDTO, Integer productId) throws Exception {
		Optional<Products> optionalproduct = productRepo.findById(productId);
		if(!optionalproduct.isPresent()) {
			throw new Exception("Product is not present");
		}
		Products products = optionalproduct.get();
		products.setName(productDTO.getName());
		products.setDescription(productDTO.getDescription());
		products.setImageURL(productDTO.getImageURL());
		products.setPrice(productDTO.getPrice());
		productRepo.save(products);
		
	}

	public Products findById(Integer productId)  throws ProductNotExistException{
		 Optional<Products> optionalProduct= productRepo.findById(productId);
		 if(optionalProduct.isPresent()) {
			 throw new ProductNotExistException("Product id is Invalid " +  productId);
		 }
		 return optionalProduct.get();
		
	}
	}
