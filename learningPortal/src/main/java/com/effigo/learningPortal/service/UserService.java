package com.effigo.learningPortal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effigo.learningPortal.dto.LoginUserDto;
import com.effigo.learningPortal.dto.RegisterUserDto;
import com.effigo.learningPortal.entity.Role;
import com.effigo.learningPortal.entity.User;
import com.effigo.learningPortal.mapper.UserMapper;
import com.effigo.learningPortal.repository.RoleRepository;
import com.effigo.learningPortal.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class UserService {
	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final RoleRepository roleRepository;
	@Autowired
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userMapper = userMapper;
	}

	public boolean registerUser(RegisterUserDto registerUser) {

		User user = userRepository.findThroughEmail(registerUser.getEmail());
		if (user != null)
			return false;

		user = userMapper.registerUserDtoToUser(registerUser);
		Role defaultRole = roleRepository.findByRoleType("Learner");

		if (defaultRole == null) {
			defaultRole = new Role();
			defaultRole.setRoleType("Learner");
			System.out.println(defaultRole);
			roleRepository.save(defaultRole);
		}
		user.getRoles().add(defaultRole);
		userRepository.save(user);

		return true;

	}

	public boolean loginUser(LoginUserDto loginUser) {

		try {
			User user = userRepository.findThroughEmail(loginUser.getEmail());
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