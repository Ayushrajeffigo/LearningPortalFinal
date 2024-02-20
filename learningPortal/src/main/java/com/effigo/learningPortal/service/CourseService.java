package com.effigo.learningPortal.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.effigo.learningPortal.dto.CourseDto;
import com.effigo.learningPortal.dto.GetCourseDto;
import com.effigo.learningPortal.entity.Category;
import com.effigo.learningPortal.entity.Courses;
import com.effigo.learningPortal.entity.CoursesCategory;
import com.effigo.learningPortal.mapper.CourseMapper;
import com.effigo.learningPortal.repository.CategoryRepository;
import com.effigo.learningPortal.repository.CourseRepository;
import com.effigo.learningPortal.repository.CoursesCategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseService {

	private final CourseRepository courseRepository;

	private final CategoryRepository categoryRepository;

	private final CoursesCategoryRepository coursesCategoryRepository;

	private final CourseMapper courseMapper;

	public CourseService(CourseRepository courseRepository, CategoryRepository categoryRepository,
			CoursesCategoryRepository coursesCategoryRepository, CourseMapper courseMapper) {
		super();
		this.courseRepository = courseRepository;
		this.categoryRepository = categoryRepository;
		this.coursesCategoryRepository = coursesCategoryRepository;
		this.courseMapper = courseMapper;
	}

	public boolean addCourse(CourseDto courseDto) {

		Optional<Category> categoryObj = categoryRepository.findByCategoryType(courseDto.getCategory());

		if (categoryObj.isEmpty()) {
			return false;
		}

		Category category = categoryObj.get();
		Courses course = courseMapper.courseDtoToCourses(courseDto);

		CoursesCategory obj = new CoursesCategory();
		obj.setCategory(category);
		obj.setCourse(courseRepository.save(course));
		coursesCategoryRepository.save(obj);

		log.info("Course added successfully: {}", course);
		return true;
	}

	public Optional<List<CoursesCategory>> getCourse(GetCourseDto getCourseDto) {
		Optional<Category> categoryObject = categoryRepository.findByCategoryType(getCourseDto.getCategory());

		if (categoryObject.isEmpty()) {
			return Optional.empty();
		}

		Category category = categoryObject.get();

		List<CoursesCategory> courses = coursesCategoryRepository.findByCategory(category);
		log.info("Retrieved {} courses for category: {}", courses.size(), category.getCategoryType());
		return Optional.of(courses);
	}

	public ResponseEntity<?> getCourseId(Long id, GetCourseDto getCourseDto) {
		List<CoursesCategory> courses = getCourse(getCourseDto).orElse(Collections.emptyList());

		for (CoursesCategory course : courses) {
			if (course.getId().equals(id)) {
				return new ResponseEntity<>(course, HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(Collections.singletonMap("Message", "Course id doesn't exist"),
				HttpStatus.BAD_REQUEST);
	}
}