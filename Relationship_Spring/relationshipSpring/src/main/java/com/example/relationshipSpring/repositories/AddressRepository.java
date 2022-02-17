package com.example.relationshipSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.relationshipSpring.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
