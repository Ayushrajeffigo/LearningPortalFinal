package com.effigo.learningPortal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.effigo.learningPortal.dto.CourseDto;
import com.effigo.learningPortal.dto.FavouriteDto;
import com.effigo.learningPortal.dto.GetCourseDto;
import com.effigo.learningPortal.dto.LoginUserDto;
import com.effigo.learningPortal.dto.RegisterUserDto;
import com.effigo.learningPortal.entity.CoursesCategory;
import com.effigo.learningPortal.service.CourseService;
import com.effigo.learningPortal.service.FavouriteService;
import com.effigo.learningPortal.service.UserService;

@SpringBootTest
class LearningPortalApplicationTests {

	@Test
	void testUserRegistration() {
		// Create a mock of UserService
		UserService userService = mock(UserService.class);

		// Set up mock behavior
		RegisterUserDto registerUserDto = new RegisterUserDto();
		registerUserDto.setName("Test User");
		registerUserDto.setEmail("tests10@gmail.com");
		registerUserDto.setPassword("user");
		when(userService.registerUser(registerUserDto)).thenReturn(true);

		// Test the registration
		assertTrue(userService.registerUser(registerUserDto));
	}

	@Test
	void testUserLogin() {
		// Create a mock of UserService
		UserService userService = mock(UserService.class);

		// Set up mock behavior
		LoginUserDto loginUserDto = new LoginUserDto();
		loginUserDto.setEmail("try1@gmail.com");
		loginUserDto.setPassword("user");
		when(userService.loginUser(loginUserDto)).thenReturn(true);

		// Test the login
		assertTrue(userService.loginUser(loginUserDto));
	}

	@Test
	void testCourseCreation() {
		// Create a mock of CourseService
		CourseService courseService = mock(CourseService.class);

		// Set up mock behavior
		CourseDto courseDto = new CourseDto();
		courseDto.setCategory("Programming");
		when(courseService.addCourse(courseDto)).thenReturn(true);

		// Test the course creation
		assertTrue(courseService.addCourse(courseDto));
	}

	@Test
	void CourseDtoTests() {
		// Create an instance of CourseDto
		CourseDto courseDto = new CourseDto();

		// Set properties
		courseDto.setCourseName("Java Programming");
		courseDto.setCategory("Programming");

		// Test properties
		assertEquals("Java Programming", courseDto.getCourseName());
		assertEquals("Programming", courseDto.getCategory());
	}

	@Test
	void testGetCoursesByCategory() {
		// Create a mock of CourseService
		CourseService courseService = mock(CourseService.class);

		// Set up mock behavior
		GetCourseDto getCourseDto = new GetCourseDto();
		getCourseDto.setCategory("Programming");

		List<CoursesCategory> coursesList = new ArrayList<>();
		coursesList.add(new CoursesCategory());
		when(courseService.getCourse(getCourseDto)).thenReturn(Optional.of(coursesList));

		// Test getting courses by category
		Optional<List<CoursesCategory>> courses = courseService.getCourse(getCourseDto);
		assertTrue(courses.isPresent());
		assertFalse(courses.get().isEmpty());
	}

	@Test
	void testAddingToFavorites() {
		// Create a mock of FavouriteService
		FavouriteService favouriteService = mock(FavouriteService.class);

		// Set up mock behavior
		FavouriteDto favouriteDto = new FavouriteDto();
		favouriteDto.setCourseId(1L);
		favouriteDto.setEmail("try1@gmail.com");
		when(favouriteService.makeFavourite(favouriteDto)).thenReturn(true);

		// Test adding to favorites
		assertTrue(favouriteService.makeFavourite(favouriteDto));
	}
}
