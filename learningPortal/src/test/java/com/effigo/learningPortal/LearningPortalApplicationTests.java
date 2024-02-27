package com.effigo.learningPortal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.effigo.learningPortal.dto.CategoryDto;
import com.effigo.learningPortal.dto.CourseDto;
import com.effigo.learningPortal.dto.FavouriteDto;
import com.effigo.learningPortal.dto.GetCourseDto;
import com.effigo.learningPortal.dto.LoginUserDto;
import com.effigo.learningPortal.dto.RegisterUserDto;
import com.effigo.learningPortal.entity.CoursesCategory;
import com.effigo.learningPortal.service.CategoryService;
import com.effigo.learningPortal.service.CourseService;
import com.effigo.learningPortal.service.FavouriteService;
import com.effigo.learningPortal.service.UserService;

@SpringBootTest
class LearningPortalApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private FavouriteService favouriteService;

	@Test
	void contextLoads() {
		assertTrue(true); // Just to ensure the application context loads successfully
	}

	@Test
	void testUserRegistration() {
	    RegisterUserDto registerUserDto = new RegisterUserDto();
	    registerUserDto.setName("Test User");
	    registerUserDto.setEmail("test@example.com");
	    registerUserDto.setPassword("password");
	    // Add roles and courses if necessary

	    boolean registrationResult = userService.registerUser(registerUserDto);
	    assertTrue(registrationResult);
	}


	@Test
	void testUserLogin() {
		// Create a LoginUserDto object with a valid email and password
		LoginUserDto loginUserDto = new LoginUserDto();
		loginUserDto.setEmail("test@example.com"); // Valid email address
		loginUserDto.setPassword("password"); // Valid password

		// Call the loginUser() method with the LoginUserDto object
		assertTrue(userService.loginUser(loginUserDto));
	}

	@Test
	void testCourseCreation() {
		// Predefined category
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategoryType("Test Category");
		assertTrue(categoryService.addCategory(categoryDto));

		// Predefined course
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseName("Test Course");
		courseDto.setCategory("Test Category");

		assertTrue(courseService.addCourse(courseDto));
	}

	@Test
	void CourseDtoTests() {
		// Create a CourseDto object
		CourseDto courseDto = new CourseDto();

		// Set values using setters
		courseDto.setCourseName("Java Programming");
		courseDto.setCategory("Programming");

		// Verify values using getters
		assertEquals("Java Programming", courseDto.getCourseName());
		assertEquals("Programming", courseDto.getCategory());
	}

	@Test
	void testGetCoursesByCategory() {
		// Create a GetCourseDto object with a valid category
		GetCourseDto getCourseDto = new GetCourseDto();
		getCourseDto.setCategory("Programming"); // Valid category

		// Call the method responsible for retrieving courses by category
		Optional<List<CoursesCategory>> courses = courseService.getCourse(getCourseDto);

		// Assert that the method returns a non-empty list of courses
		assertTrue(courses.isPresent());
		assertFalse(courses.get().isEmpty());
	}

	@Test
	void testAddingToFavorites() {
		// Create a FavouriteDto object with a valid email and course ID
		FavouriteDto favouriteDto = new FavouriteDto();
		favouriteDto.setCourseId(1L); // Assuming course ID 1 is valid
		favouriteDto.setEmail("test@example.com"); // Valid email address

		// Call the makeFavourite() method with the FavouriteDto object
		assertTrue(favouriteService.makeFavourite(favouriteDto));
	}
}
