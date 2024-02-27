package com.effigo.learningPortal.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.effigo.learningPortal.dto.LoginUserDto;
import com.effigo.learningPortal.dto.RegisterUserDto;
import com.effigo.learningPortal.entity.User;
import com.effigo.learningPortal.mapper.UserMapper;
import com.effigo.learningPortal.repository.RoleRepository;
import com.effigo.learningPortal.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional

public class UserService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userMapper = userMapper;
	}

	public boolean registerUser(RegisterUserDto registerUserDto) {
		try {
			User user = userMapper.registerUserDtoToUser(registerUserDto);
			userRepository.save(user);
			return true; // Registration successful
		} catch (Exception e) {
			// Log the exception or handle it according to your application's logic
			return false; // Registration failed
		}
	}

	public boolean loginUser(LoginUserDto loginUser) {

		try {
			User user = userRepository.findThroughEmail(loginUser.getEmail());
			RegisterUserDto userDetails = userMapper.userToRegisterUserDto(user);

			if (userDetails == null || userDetails.getPassword().equals(loginUser.getPassword())) {
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