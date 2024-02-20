package com.effigo.learningPortal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.effigo.learningPortal.entity.Category;
import com.effigo.learningPortal.entity.CoursesCategory;

public interface CoursesCategoryRepository extends JpaRepository<CoursesCategory, Long> {

	List<CoursesCategory> findByCategory(Category category);
}