package com.demo.springsecurityfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.springsecurityfull.entity.PasswordResetToken;

/**
 * @author: KhanhPG
 * @since: 28/02/2022
 */
@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	PasswordResetToken findByToken(String token);

}
