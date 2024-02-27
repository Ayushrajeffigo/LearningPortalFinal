package com.effigo.learningPortal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.effigo.learningPortal.dto.LoginUserDto;
import com.effigo.learningPortal.dto.RegisterUserDto;
import com.effigo.learningPortal.entity.User;
import com.effigo.learningPortal.mapper.UserMapper;
import com.effigo.learningPortal.repository.RoleRepository;
import com.effigo.learningPortal.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userMapper = userMapper;
	}

	@Transactional
	public boolean registerUser(RegisterUserDto registerUserDto) {
		try {
			// Check if a user with the same email already exists
			if (userRepository.existsByEmail(registerUserDto.getEmail())) {
				log.warn("User with email {} already exists.", registerUserDto.getEmail());
				return false; // User already exists, registration failed
			} else {

				User user = userMapper.registerUserDtoToUser(registerUserDto);
				userRepository.save(user);
				return true; // Registration successful
			}
		} catch (Exception e) {
			log.error("Error occurred during user registration.", e);
			return false; // Registration failed
		}
	}

	@Transactional(readOnly = true)
	public boolean loginUser(LoginUserDto loginUser) {
		try {
			User user = userRepository.findThroughEmail(loginUser.getEmail());
			if (user == null) {
				log.warn("User not found with email: {}", loginUser.getEmail());
				return false;
			}

			RegisterUserDto userDetails = userMapper.userToRegisterUserDto(user);
			if (userDetails.getPassword().equals(loginUser.getPassword())) {
				log.info("User logged in successfully: {}", loginUser.getEmail());
				return true;
			} else {
				log.warn("User authentication failed for email: {}", loginUser.getEmail());
				return false;
			}
		} catch (Exception e) {
			log.error("Error occurred during user authentication.", e);
			return false;
		}
	}
}
