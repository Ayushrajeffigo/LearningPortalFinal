package com.effigo.learningPortal.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigo.learningPortal.dto.CourseDto;
import com.effigo.learningPortal.dto.GetCourseDto;
import com.effigo.learningPortal.entity.CoursesCategory;
import com.effigo.learningPortal.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	private final CourseService courseService;
	private static final String MESSAGE_KEY = "Message";

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createCourses(@Valid @RequestBody CourseDto courseDto) {
		boolean create = courseService.addCourse(courseDto);
		if (create) {
			return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, "Course added successfully"),
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, "Some error occurred"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/view")
	public ResponseEntity<?> viewCourses(@Valid @RequestBody GetCourseDto getCourseDto) {
		Optional<List<CoursesCategory>> response = courseService.getCourse(getCourseDto);

		if (response.isEmpty()) {
			return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, "Category does not exist"),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(response.get(), HttpStatus.OK);
	}

	@PostMapping("/view/{id}")
	public ResponseEntity<?> viewCourseById(@Valid @PathVariable Long id, @RequestBody GetCourseDto getCourseDto) {
		return courseService.getCourseId(id, getCourseDto);
	}
}