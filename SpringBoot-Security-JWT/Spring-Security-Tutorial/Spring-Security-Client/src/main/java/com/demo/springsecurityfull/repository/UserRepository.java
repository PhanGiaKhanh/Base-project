package com.demo.springsecurityfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.springsecurityfull.entity.User;

/**
* @author: KhanhPG
* @since: 28/02/2022	
*/
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
