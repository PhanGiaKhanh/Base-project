package com.example.relationshipSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.relationshipSpring.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
