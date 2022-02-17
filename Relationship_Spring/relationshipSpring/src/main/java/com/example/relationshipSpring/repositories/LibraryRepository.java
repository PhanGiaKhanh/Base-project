package com.example.relationshipSpring.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.relationshipSpring.models.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {
	@Modifying
	@Transactional
	@Query (value = "INSERT INTO libraries (name, address_id) VALUES ( ?1 , ?2 )", nativeQuery = true)
	void insertLibrary(String name, Integer id);
}
