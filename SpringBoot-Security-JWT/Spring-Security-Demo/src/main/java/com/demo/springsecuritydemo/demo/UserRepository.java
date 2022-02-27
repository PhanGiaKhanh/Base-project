package com.demo.springsecuritydemo.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @author: KhanhPG
* @since: 27/02/2022	
*/
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
