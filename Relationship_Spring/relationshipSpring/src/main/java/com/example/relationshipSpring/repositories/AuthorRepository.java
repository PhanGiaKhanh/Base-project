package com.example.relationshipSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.relationshipSpring.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
