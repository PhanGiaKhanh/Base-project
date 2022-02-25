package com.example.demoEmail.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demoEmail.registration.token.ConfirmationToken;
import com.example.demoEmail.registration.token.ConfirmationTokenService;

/**
 * @author: KhanhPG
 * @since: 24/02/2022
 */
@Service
public class AppUserService implements UserDetailsService {
	private static final String USER_NOT_FOUND_MSG = "user with email %s not found";
	private final AppUserRepository appUserRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	public AppUserService(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}

	public String signUpUser(AppUser appUser) {
		boolean userExists = appUserRepository.findByEmail(appUser.getUsername()).isPresent();
		if(userExists) {
			throw new IllegalStateException("email already taken");
		}
		String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
		appUser.setPassword(encodedPassword);
		
		appUserRepository.save(appUser);
		String token = UUID.randomUUID().toString();
		// Send confirmation token
		ConfirmationToken confirmationToken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				appUser);
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		// TODO: SEND EMAIL
		return token + "\n"+ appUser.toString();
	}

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
