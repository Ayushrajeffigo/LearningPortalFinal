package com.effigo.learningPortal.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigo.learningPortal.dto.LoginUserDto;
import com.effigo.learningPortal.dto.RegisterUserDto;
import com.effigo.learningPortal.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private final UserService userService;
	private static final String MESSAGE_KEY = "Message";

	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
		boolean register = userService.registerUser(registerUserDto);
		HashMap<String, String> response = new HashMap<>();

		if (register) {
			response.put(MESSAGE_KEY, "User registered successfully.");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.put(MESSAGE_KEY, "User already exists");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto loginUserDto) {
		boolean login = userService.loginUser(loginUserDto);
		HashMap<String, String> response = new HashMap<>();

		if (login) {
			response.put(MESSAGE_KEY, "User logged in successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.put(MESSAGE_KEY, "User authentication failed");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}