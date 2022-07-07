package com.CodeMastr.Ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CodeMastr.Ecommerce.Models.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

	

}
