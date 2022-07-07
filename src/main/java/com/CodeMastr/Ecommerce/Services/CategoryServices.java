package com.CodeMastr.Ecommerce.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CodeMastr.Ecommerce.Models.Category;
import com.CodeMastr.Ecommerce.Repository.CategoryRepo;

@Service
public class CategoryServices {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	public void createCategory(Category category) {
		Category data = new Category();
		data.setCategoryName(category.getCategoryName());
		data.setDescription(category.getDescription());
		data.setImageURL(category.getImageURL());
		categoryRepo.save(data);
	}
	
	public List<Category> listCategory(){
		return categoryRepo.findAll();
	}

	public void editCategory(int category_id, Category updateCategoryById) {
		Category category =categoryRepo.getById(category_id);
		category.setCategoryName(updateCategoryById.getCategoryName());
		category.setDescription(updateCategoryById.getDescription());
		category.setImageURL(updateCategoryById.getImageURL());
		categoryRepo.save(category);
		
	}

	public boolean findById(int category_id) {
		return categoryRepo.findById(category_id).isPresent();
	}
	

		
	}
	
	
	
		
	


	
