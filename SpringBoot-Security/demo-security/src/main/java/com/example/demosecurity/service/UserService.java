package com.example.demosecurity.service;

import java.util.List;

import com.example.demosecurity.models.Role;
import com.example.demosecurity.models.User;

public interface UserService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	User getUser(String username);
	List<User> getUsers();           
}
