package com.effigo.learningPortal.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.effigo.learningPortal.dto.FavouriteDto;
import com.effigo.learningPortal.entity.Courses;
import com.effigo.learningPortal.entity.User;
import com.effigo.learningPortal.repository.CourseRepository;
import com.effigo.learningPortal.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FavouriteService {

	private final CourseRepository courseRepository;

	private final UserRepository userRepository;

	public FavouriteService(CourseRepository courseRepository, UserRepository userRepository) {
		this.courseRepository = courseRepository;
		this.userRepository = userRepository;
	}

	public boolean makeFavourite(FavouriteDto favouriteDto) {
		try {
			// Find the course by ID
			Optional<Courses> courseOptional = courseRepository.findById(favouriteDto.getCourseId());
			if (courseOptional.isEmpty()) {
				log.error("Failed to add course to favorites. Course with ID {} not found.",
						favouriteDto.getCourseId());
				return false;
			}

			// Find the user by email
			User user = userRepository.findThroughEmail(favouriteDto.getEmail());
			if (user == null) {
				log.error("Failed to add course to favorites. User with email {} not found.", favouriteDto.getEmail());
				return false;
			}

			Courses course = courseOptional.get();

			// Add the course to the user's favorites
			Set<Courses> favorites = user.getFavourites();
			favorites.add(course);
			user.setFavourites(favorites);

			// Save the updated user entity
			userRepository.save(user);

			log.info("Course with ID {} added to favorites for user with email {}", favouriteDto.getCourseId(),
					favouriteDto.getEmail());
			return true;
		} catch (Exception e) {
			log.error("Error occurred while adding course to favorites.", e);
			return false;
		}
	}

	public boolean removeFavourite(FavouriteDto favouriteDto) {
		try {
			Optional<Courses> courseOptional = courseRepository.findById(favouriteDto.getCourseId());
			if (courseOptional.isEmpty()) {
				log.error("Course with ID {} not found.", favouriteDto.getCourseId());
				return false;
			}

			String email = favouriteDto.getEmail();
			User user = userRepository.findThroughEmail(email);
			if (user == null) {
				log.error("User with email {} not found.", email);
				return false;
			}

			Courses course = courseOptional.get();
			Set<Courses> favorites = user.getFavourites();
			if (!favorites.contains(course)) {
				log.error("Course with ID {} is not in favorites for user with email {}.", course.getId(), email);
				return false;
			}

			favorites.remove(course);
			user.setFavourites(favorites);
			userRepository.save(user);

			log.info("Course with ID {} removed from favorites for user with email {}", favouriteDto.getCourseId(),
					favouriteDto.getEmail());
			return true;
		} catch (Exception e) {
			log.error("Error occurred while removing course from favorites.", e);
			return false;
		}
	}
}
