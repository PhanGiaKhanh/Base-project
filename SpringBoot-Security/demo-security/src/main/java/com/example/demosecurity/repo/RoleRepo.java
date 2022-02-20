package com.example.demosecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demosecurity.models.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
