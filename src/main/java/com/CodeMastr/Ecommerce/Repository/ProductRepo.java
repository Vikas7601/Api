package com.CodeMastr.Ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CodeMastr.Ecommerce.Models.Products;

@Repository
public interface ProductRepo extends JpaRepository<Products,Integer> {

}
