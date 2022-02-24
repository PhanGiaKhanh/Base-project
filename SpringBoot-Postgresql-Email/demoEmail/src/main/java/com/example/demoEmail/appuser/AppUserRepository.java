package com.example.demoEmail.appuser;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
* @author: KhanhPG
* @since: 24/02/2022	
*/
@Repository
@Transactional(readOnly = true)
public interface AppUserRepository {
	Optional<AppUser> findByEmail(String email);
}
