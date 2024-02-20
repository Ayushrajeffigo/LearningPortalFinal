package com.effigo.learningPortal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.effigo.learningPortal.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(value = "SELECT * FROM course_category WHERE category_type = :category", nativeQuery = true)
	Optional<Category> findByCategoryType(@Param("category") String category);
}
