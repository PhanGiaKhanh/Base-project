package com.KhanhPG.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KhanhPG.springboot.models.User;

/**
 * @author: KhanhPG
 * @since: 21/02/2022
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
