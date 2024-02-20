package com.effigo.learningPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.effigo.learningPortal.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	// :eml :- a variable where we can store the result
	// param used to bind the query result into the defined variable
	@Query(value = "SELECT * FROM user_details WHERE email = :eml", nativeQuery = true)
	User findThroughEmail(@Param("eml") String email);

}