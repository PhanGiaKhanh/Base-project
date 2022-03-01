package com.demo.springsecurityfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.springsecurityfull.entity.VerificationToken;

/**
* @author: KhanhPG
* @since: 28/02/2022	
*/
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

	VerificationToken findByToken(String token);

}
