package com.CodeMastr.Ecommerce.Controllers;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CodeMastr.Ecommerce.Models.Category;
import com.CodeMastr.Ecommerce.Services.CategoryServices;
import com.CodeMastr.Ecommerce.common.ApiResponse;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryServices categoryServices;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
		categoryServices.createCategory(category);
		return new ResponseEntity<>(new ApiResponse(true, "New Category created"),HttpStatus.CREATED);
	}
	
	@GetMapping("/get_all_category")
	public List<Category> listCategory() {
		 return categoryServices.listCategory();
		
	}
	
	@PutMapping("/update/{category_id}")
	public ResponseEntity<ApiResponse> updateCategoryById(@PathVariable("category_id") int category_id,@RequestBody Category category) {
			if(!categoryServices.findById(category_id)) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category having id " + category_id + " does not found"),HttpStatus.NOT_FOUND);
			}
			categoryServices.editCategory(category_id,category);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Category having id " + category_id + " has been updated"),HttpStatus.OK);
	}
	
	
}
