package com.CodeMastr.Ecommerce.Repository;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.CodeMastr.Ecommerce.Models.Users;


@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
	
	Users findByEmail(String email);
		
}
