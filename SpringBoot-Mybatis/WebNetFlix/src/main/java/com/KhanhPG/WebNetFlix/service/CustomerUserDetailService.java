package com.KhanhPG.WebNetFlix.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.KhanhPG.WebNetFlix.mapper.UserMapper;
import com.KhanhPG.WebNetFlix.model.User;
import com.KhanhPG.WebNetFlix.model.UserExample;

@Service
public class CustomerUserDetailService implements UserDetailsService {
	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(username);
		List<User> users = userMapper.selectByExample(example);
		if (!users.isEmpty()) {
			User user = users.get(0);
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN"); // role Admin nếu có role User tạo thêm gán vào list
			grantedAuthorities.add(authority);
			return new org.springframework.security.core.userdetails.User(user.getFullName(),
					user.getUserPassword(), grantedAuthorities);
		} else {
			throw new UsernameNotFoundException("Login failed!!!");
		}
	}

}
